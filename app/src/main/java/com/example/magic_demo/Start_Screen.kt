package com.example.magic_demo



import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toSize
import com.example.magic_demo.TestActivities.BgTest
import com.example.magic_demo.TestActivities.EnTest
import com.example.magic_demo.TestActivities.FsTest
import com.example.magic_demo.TestActivities.UpTest
import com.example.magic_demo.TestActivities.objTest
import com.example.magic_demo.bootom_navigation.BottomNavigation
import com.example.magic_demo.bootom_navigation.BottomNavigationItem
import com.example.magic_demo.bootom_navigation.MaterialTheme
import com.example.magic_demo.bootom_navigation.MetaContainer
import com.example.magic_demo.bootom_navigation.MetaEntity
import com.example.magic_demo.bootom_navigation.Surface
import com.example.magic_demo.ui.theme.Magic_demoTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.math.roundToInt


class Start_Screen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Assuming this is your custom function for edge-to-edge UI.
        setContent {
            Magic_demoTheme {


                // Call ImageSelectionScreen with the required parameters
                ImageSelectionScreen(
                    backgroundImage = painterResource(id = R.drawable.blur),
                    additionalImage = painterResource(id = R.drawable.sspring)

                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ImageSelectionScreen(
    backgroundImage: Painter,
    additionalImage: Painter,

) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    // Initial profile image painter, not directly inside remember
    var selectedProfileImageRes by remember { mutableStateOf(R.drawable.eeeee) }
    // New state for selected label
    var selectedLabel by remember { mutableStateOf("") }

    // Now use painterResource to get the painter for the selected image inside composable
    val selectedProfileImage = painterResource(id = selectedProfileImageRes)

    // Track if Face-swap is selected
    var isFaceSwapSelected by remember { mutableStateOf(false) }
    // Track if Bg Remove is selected
    var isBgRemoveSelected by remember { mutableStateOf(false) }
    // Track if Enhanced is selected
    var isEnhanceSelected by remember { mutableStateOf(false) }
    // Track if Up Scale is selected
    var isUpscaleSelected by remember { mutableStateOf(false) }
    // Track if Obj remove is selected
    var isObjremoveSelected by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            Log.d("Background", "Image URI selected: $uri")

            try {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                bitmap.value = ImageDecoder.decodeBitmap(source)
                Log.d("Background", "Bitmap created and converted to Base64")
            } catch (e: Exception) {
                Log.e("Background", "Error creating bitmap from URI: ${e.message}")
            }
        } else {
            Log.d("Background", "No image selected or operation canceled")
            Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

//    val gradientColors = listOf(Color(0xFF536DFE), Color(0xFFFF6E7F),)

    Box(modifier = Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.Black)) {
//        Image(
//            painter = backgroundImage,
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxSize()
//                .blur(16.dp) // Blur effect
//                .background(androidx.compose.ui.graphics.Color.Black)
//        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the selected label at the top of the screen
            Text(
                text = selectedLabel,
                style = TextStyle(fontSize = 24.sp,
//                    brush = Brush.linearGradient(
//                        colors = gradientColors
//
//                    ),
                    color = androidx.compose.ui.graphics.Color.Gray,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(16.dp)
            )

            Image(
                painter = selectedProfileImage,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(390.dp)
                    .clip(RectangleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-18.dp)
            ) {
                Image(
                    painter = additionalImage,
                    contentDescription = "Additional Image",
                    modifier = Modifier.size(120.dp)
                )

                Box(
                    modifier = Modifier
                        //.size(90.dp) // Adjust size as needed
                        .width(140.dp)
                        .height(50.dp)
                        .shadow(10.dp, shape = RoundedCornerShape(20.dp), ambientColor = androidx.compose.ui.graphics.Color.Black)
                        .background(Color(0xFFFFC107), shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFFFC107), // Slightly lighter shade
                        modifier = Modifier
//                                .fillMaxSize(0.85f)
                            .width(120.dp)
                            .height(40.dp)
                            .clickable {
                                launcher.launch("image/*")
                            }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "Select Image",
                                style = TextStyle(
                                    color = Black,
                                    fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }



            }
        }

        imageUri?.let {
            when {
                isBgRemoveSelected -> {
                    val intent = Intent(context, BgTest::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    context.startActivity(intent)
                }
                isEnhanceSelected -> {
                    val intent = Intent(context, EnTest::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    context.startActivity(intent)
                }
                isUpscaleSelected -> {
                    val intent = Intent(context, UpTest::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    context.startActivity(intent)
                }
                isObjremoveSelected -> {
                    val intent = Intent(context, objTest::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    context.startActivity(intent)
                }
                isFaceSwapSelected -> {
                    val intent = Intent(context, FsTest::class.java).apply {
                        putExtra("imageUri", it.toString())
                    }
                    context.startActivity(intent)
                }

            }
        }

        BottomButtonRow(
            onProfileImageChange = { selectedImageRes ->
                selectedProfileImageRes = selectedImageRes
            },
            onFaceSwapSelected = { isSelected ->
                isFaceSwapSelected = isSelected
            },
            onBgRemoveSelected = { isSelected ->
                isBgRemoveSelected = isSelected
            },
            onEnhanceSelected = { isSelected ->
                isEnhanceSelected = isSelected
            },
            onUpscaleSelected = { isSelected ->
                isUpscaleSelected = isSelected
            },
            onObjremoveSelected = { isSelected ->
                isObjremoveSelected = isSelected
            },
            onLabelChange = { newLabel ->
                selectedLabel = newLabel // Update the selected label when a button is clicked
            }
        )
    }


@Composable
fun BottomButtonRow(
    onProfileImageChange: (Int) -> Unit,
    onFaceSwapSelected: (Boolean) -> Unit,
    onBgRemoveSelected: (Boolean) -> Unit,
    onEnhanceSelected: (Boolean) -> Unit,
    onUpscaleSelected: (Boolean) -> Unit,
    onObjremoveSelected: (Boolean) -> Unit,
    onLabelChange: (String) -> Unit, // New callback for updating the selected label
) {
    var selectedButton by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(
                    androidx.compose.ui.graphics.Color.Transparent
                )
                .padding(vertical = 16.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(130.dp)
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    BottomIconButtonWithLabel(
                        label = "Enhance",
                        iconRes = painterResource(id = R.drawable.eeeee),
                        isSelected = selectedButton == "Enhance",
                        onClick = {
                            selectedButton = "Enhance"
                            onProfileImageChange(R.drawable.eeeee)
                            onFaceSwapSelected(false)
                            onBgRemoveSelected(false)
                            onUpscaleSelected(false)
                            onObjremoveSelected(false)
                            onEnhanceSelected(true)
                            onLabelChange("Enhance your Image") // Update label

                        }
                    )
                }
                item {
                    BottomIconButtonWithLabel(
                        label = "Remove Bg",
                        iconRes = painterResource(id = R.drawable.eelipse),
                        isSelected = selectedButton == "Bg remove",
                        onClick = {
                            selectedButton = "Bg remove"
                            onProfileImageChange(R.drawable.eelipse)
                            onFaceSwapSelected(false)
                            onBgRemoveSelected(true)
                            onEnhanceSelected(false)
                            onUpscaleSelected(false)
                            onObjremoveSelected(false)
                            onLabelChange("Remove Background of your Image") // Update label
                        }
                    )
                }
                item {
                    BottomIconButtonWithLabel(
                        label = "Remove Obj",
                        iconRes = painterResource(id = R.drawable.ooremove),
                        isSelected = selectedButton == "Obj-remove",
                        onClick = {
                            selectedButton = "Obj-remove"
                            onProfileImageChange(R.drawable.ooremove)
                            onFaceSwapSelected(false)
                            onBgRemoveSelected(false)
                            onEnhanceSelected(false)
                            onUpscaleSelected(false)
                            onObjremoveSelected(true)
                            onLabelChange("Remove un-wanted objects from your Image") // Update label
                        }
                    )
                }

                item {
                    BottomIconButtonWithLabel(
                        label = "Up-scale",
                        iconRes = painterResource(id = R.drawable.uupscale),
                        isSelected = selectedButton == "Up scale",
                        onClick = {
                            selectedButton = "Up scale"
                            onProfileImageChange(R.drawable.uupscale)
                            onFaceSwapSelected(false)
                            onBgRemoveSelected(false)
                            onEnhanceSelected(false)
                            onUpscaleSelected(true)
                            onObjremoveSelected(false)
                            onLabelChange("Up-scale your Image") // Update label
                        }
                    )
                }
                item {
                    BottomIconButtonWithLabel(
                        label = "Swap Faces",
                        iconRes = painterResource(id = R.drawable.ffswap),
                        isSelected = selectedButton == "Face-swap",
                        onClick = {
                            selectedButton = "Face-swap"
                            onProfileImageChange(R.drawable.ffswap)
                            onFaceSwapSelected(true)
                            onBgRemoveSelected(false)
                            onEnhanceSelected(false)
                            onUpscaleSelected(false)
                            onObjremoveSelected(false)
                            onLabelChange("Swap Faces in your Image") // Update label
                        }
                    )
                }
            }
        }
    }
}
/*
@Composable
fun BottomIconButtonWithLabel(
    label: String,
    iconRes: Painter,
    isSelected: Boolean, // Track if the button is selected
    onClick: () -> Unit
) {
    // Smooth animated size for the icon with custom tween
    val animatedSize by animateDpAsState(
        targetValue = if (isSelected) 84.dp else 40.dp,
    )

// Your colors for the gradient
    val color1 = androidx.compose.ui.graphics.Color.Gray
    val color2 = androidx.compose.ui.graphics.Color.DarkGray

// Define a custom tween
    val tweenSpec = TweenSpec<Color>(durationMillis = 1000) // You can adjust the duration

    // Animated background gradient color
    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) color2 else color1,
        animationSpec = tweenSpec
    )

// Animated text gradient color
    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) Black else Black,
        animationSpec = tweenSpec
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(64.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(animatedSize) // Apply animated size
                .clip(CircleShape)
                .background(animatedBackgroundColor) // Apply animated background color
        ) {
            Image(
                painter = iconRes,
                contentDescription = label,
                modifier = Modifier.size(animatedSize) // Apply animated size
            )
        }

        if (label.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                color = White// Apply animated text color
            )
        }
    }
}
*/

@Composable
fun BottomIconButtonWithLabel(
    label: String,
    iconRes: Painter,
    isSelected: Boolean, // Track if the button is selected
    onClick: () -> Unit
) {
    // Smooth animated size for the icon with custom tween
    val animatedSize by animateDpAsState(
        targetValue = if (isSelected) 84.dp else 40.dp,
    )

    // Your colors for the gradient
    val color1 = androidx.compose.ui.graphics.Color.Gray
    val color2 = androidx.compose.ui.graphics.Color.DarkGray

    // Define a custom tween
    val tweenSpec = TweenSpec<Color>(durationMillis = 1000) // You can adjust the duration

    // Animated background gradient color
    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) color2 else color1,
        animationSpec = tweenSpec
    )

    // Animated text gradient color
    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) Black else Black,
        animationSpec = tweenSpec
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(85.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(animatedSize)
                .clip(CircleShape)
                .background(animatedBackgroundColor)
        ) {
            Image(
                painter = iconRes,
                contentDescription = label,
                modifier = Modifier.size(animatedSize)
            )
        }

        if (label.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = label,
                fontSize = 12.sp,
                color = White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen() {
    ImageSelectionScreen(
        backgroundImage = painterResource(id = R.drawable.blur),
        additionalImage = painterResource(id = R.drawable.sspring),
    )
}

