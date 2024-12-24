package com.example.magic_demo

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent


import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore

import androidx.compose.material3.Text

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*

import androidx.compose.animation.core.*
import androidx.compose.foundation.*

import androidx.compose.material3.Icon

import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
//import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.PointerInputChange

import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.core.os.BuildCompat
import com.example.magic_demo.demo.ImageWithConstraints
import com.example.magic_demo.demo.MotionEvent
import com.example.magic_demo.demo.pointerMotionEvents
import com.google.modernstorage.photopicker.PhotoPicker

import okhttp3.OkHttpClient
import android.util.Base64

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import okhttp3.Call
import okhttp3.Callback
import org.json.JSONObject
import okhttp3.Response
import java.io.IOException
import okhttp3.Request
import androidx.compose.ui.unit.sp
import androidx.core.util.TypedValueCompat.dpToPx
import com.example.magic_demo.beforeafter.BeforeAfterImage
import com.example.magic_demo.beforeafter.ContentOrder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import com.example.magic_demo.iDp2Px
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.math.roundToInt



//var is_true_obj = false
//var globalBitmap_obj: Bitmap? = null
//var originalBitmap_obj: Bitmap? = null // Variable to hold the original image
//var processedBitmap_obj: Bitmap? = null // Variable to hold the processed image
/////-----------------------------------------------------------------------------------------------
class RemovalActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Get the image URI passed from the previous activity
//            val imageUri = intent.getStringExtra("IMAGE_URI")?.let { Uri.parse(it) }
//
//            globalBitmap_obj = null
//            originalBitmap_obj = null
//            processedBitmap_obj = null
//            is_true_obj = false

//            Removal(imageUri.toString()) // Pass the image URI to your composable

        }
    }
}
//
//@Composable
//@SuppressLint("UnusedBoxWithConstraintsScope")
//@RequiresApi(Build.VERSION_CODES.P)
//fun Removal(uri: String) {
//    val context = LocalContext.current
//    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
//    var base64String by remember { mutableStateOf("") }
//    var base64Image by remember { mutableStateOf("") }
//    var imageSize by remember { mutableStateOf<Pair<Int, Int>?>(null) }
//    var isTrue_obj by remember { mutableStateOf(false) }
//    var processedBitmap_obj by remember { mutableStateOf<Bitmap?>(null) }
//    var isLoading by remember { mutableStateOf(false) } // Add state for loading
//    var isImageSaved by remember { mutableStateOf(false) }
//
//    // Load the Bitmap asynchronously
//    LaunchedEffect(uri) {
//        try {
//            val parsedUri = Uri.parse(uri)
//            val source = ImageDecoder.createSource(context.contentResolver, parsedUri)
//            val bitmap = ImageDecoder.decodeBitmap(source)
//            imageBitmap = bitmap.asImageBitmap()
//            base64String = bitmapToBase68(bitmap)
//            imageSize = Pair(bitmap.width, bitmap.height)
//        } catch (e: Exception) {
//            Log.e("RemovalActivity", "Error decoding image from URI: ${e.message}")
//        }
//    }
//
//    fun sendSecondApiiiiCall(
//        base64String: String,
//        base64Image: String,
//        imageSize: Pair<Int, Int>,
//        onProcessedBitmap: (Bitmap) -> Unit // Callback to pass the processed Bitmap
//    ) {
//        isLoading = true // Start loading animation
//
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(5, TimeUnit.MINUTES)
//            .writeTimeout(5, TimeUnit.MINUTES)
//            .readTimeout(5, TimeUnit.MINUTES)
//            .build()
//
//        val jsonData = JSONObject().apply {
//            put("mask", base64Image)
//            put("image", base64String)
//            put("size", JSONObject().apply {
//                put("width", imageSize.first)
//                put("height", imageSize.second)
//            })
//        }
//        val requestBody = jsonData.toString().toRequestBody("application/json".toMediaTypeOrNull())
//        val request = Request.Builder()
//            .url("https://0a83-39-43-156-91.ngrok-free.app/removeobj")  // Your API endpoint
//            .post(requestBody)
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("API Call", "Failed to send API call", e)
//                isLoading = false // Stop loading animation
//            }
//            override fun onResponse(call: Call, response: Response) {
//                response.body?.string()?.let { responseBody ->
//                    try {
//                        val newobj = JSONObject(responseBody)
//                        val image = newobj.getString("bg_image")
//
//                        val imageBytes = Base64.decode(image, Base64.DEFAULT)
//                        val processedBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//
//                        // Pass the processed bitmap back to the Composable
//                        onProcessedBitmap(processedBitmap)
//                    } catch (e: Exception) {
//                        Log.e("API Response", "Error parsing response", e)
//                    }
//                }
//                response.close()
//                isLoading = false // Stop loading animation
//            }
//        })
//    }
//
//
//    Scaffold(
//        floatingActionButtonPosition = FabPosition.End,
//        content = { paddingValues: PaddingValues ->
//            Box(
//                modifier = Modifier
//                    .padding(paddingValues)
//                    .fillMaxSize()
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    // Conditionally display BeforeAfterImageDemo
//                    if (processedBitmap_obj != null) {
//                        BeforeAfterImageDemo(
//                            beforeImage = imageBitmap,
//                            afterImage = processedBitmap_obj?.asImageBitmap()
//                        )
//                    } else {
//                        // Show original image
//                        imageBitmap?.let {
//                            ImageScale(imageBitmap = it, base64String) { updatedBase64Image ->
//                                base64Image = updatedBase64Image
//                            }
//                        }
//                    }
//                }
//
//                // Show loading animation
//                if (isLoading) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(Color.Black.copy(alpha = 0.5f))
//                            .wrapContentSize(Alignment.Center)
//                    ) {
//                        CircularProgressIndicator(color = Color.White)
//                    }
//                }
//            }
//        },
//        floatingActionButton = {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 22.dp), // Add horizontal padding
//                verticalArrangement = Arrangement.spacedBy(1.dp) // Add spacing between items
//            ) {
//                Button(
//                    onClick = {
//                        if (base64String.isNotEmpty() && base64Image.isNotEmpty() && imageSize != null) {
//                            sendSecondApiiiiCall(
//                                base64String,
//                                base64Image,
//                                imageSize!!
//                            ) { processedBitmap ->
//                                processedBitmap_obj = processedBitmap
//                                Log.d("Image Update", "Processed image received and updated in UI")
//                            }
//                        } else {
//                            Log.d("RemovalActivity", "Data is missing for API call")
//                        }
//                    },
//                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF7986CB)
//                    ),
//                    modifier = Modifier
//                        .width(390.dp)
//                        .height(55.dp)
//                ) {
//                    Text(
//                        "Remove-Object",
//                        color = androidx.compose.ui.graphics.Color.Black,
//                    )
//                    Spacer(modifier = Modifier.weight(1.1f))
//                    Icon(
//                        painter = painterResource(id = R.drawable.proceed),
//                        contentDescription = null,
//                        tint = androidx.compose.ui.graphics.Color.Unspecified,
//                        modifier = Modifier.size(40.dp)
//                    )
//                }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//                Button(
//                    onClick = {
//                        processedBitmap_obj?.let { bitmap ->
//
//                            val contentValues = ContentValues().apply {
//                                put(
//                                    MediaStore.Images.Media.DISPLAY_NAME,
//                                    "image_${System.currentTimeMillis()}.png"
//                                )
//                                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
//                            }
//
//                            val resolver = context.contentResolver
//                            val uri = resolver.insert(
//                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                contentValues
//                            )
//
//                            uri?.let { imageUri ->
//                                resolver.openOutputStream(imageUri).use { outputStream ->
//                                    if (outputStream != null) {
//                                        bitmap.compress(
//                                            Bitmap.CompressFormat.PNG,
//                                            100,
//                                            outputStream
//                                        )
//                                    }
//                                }
//
//                                Toast.makeText(
//                                    context,
//                                    "Image saved successfully!",
//                                    Toast.LENGTH_SHORT
//                                )
//                                    .show()
//                                processedBitmap_obj = null
//                            }
//                        }
//                    },
//                    enabled = !isImageSaved,
//                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF7986CB)
//                    ),
//                    modifier = Modifier
//                        .width(390.dp)
//                        .height(55.dp)
//                ) {
//                    Text(
//                        "Save Image",
//                        color = androidx.compose.ui.graphics.Color.Black,
//                    )
//                    Spacer(modifier = Modifier.weight(1.1f))
//                    Icon(
//                        painter = painterResource(id = R.drawable.save),
//                        contentDescription = null,
//                        tint = androidx.compose.ui.graphics.Color.Unspecified,
//                        modifier = Modifier.size(40.dp)
//                    )
//
//                }
//            }
//        }
//    )
//}
//
//
//@Composable
//fun BeforeAfterImageDemo(beforeImage: ImageBitmap?, afterImage: ImageBitmap?) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(1.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//        var beforeContentScale by remember { mutableStateOf(ContentScale.Fit) }
//        var afterContentScale by remember { mutableStateOf(ContentScale.FillBounds) }
//
//        Spacer(modifier = Modifier.height(40.dp))
//
//        if (beforeImage != null && afterImage != null) {
//            BeforeAfterImage(
//                modifier = Modifier
//                    .shadow(1.dp, RoundedCornerShape(10.dp))
//                    .fillMaxWidth()
//                    .aspectRatio(4 / 6f),
//                beforeImage = beforeImage,
//                afterImage = afterImage,
//                contentOrder = ContentOrder.AfterBefore,
//                beforeContentScale = beforeContentScale,
//                afterContentScale = afterContentScale
//            )
//        }
//    }
//}
//
//@Composable
//fun ImageScale(
//    imageBitmap: ImageBitmap,
//    base64String: String,
//    onBase64ImageUpdate: (String) -> Unit // Callback to update the base64Image from Drawing
//) {
//    var contentScale by remember { mutableStateOf(ContentScale.Fit) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//    ) {
//        val modifier = Modifier
//            .background(Color.LightGray)
//            .border(2.dp, Color.Red)
//            .fillMaxWidth()
//            .aspectRatio(4 / 6f)
//
//        DrawSample(modifier, imageBitmap, contentScale, onBase64ImageUpdate)
//    }
//}
//
//@Composable
//private fun DrawSample(
//    modifier: Modifier,
//    imageBitmap: ImageBitmap,
//    contentScale: ContentScale,
//    onBase64ImageUpdate: (String) -> Unit // Callback to update the base64Image
//) {
//    Text(
//        text = "Draw To Remove-Objects",
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Bold,
//        color = Color.Black,
//        modifier = Modifier.padding(18.dp)
//    )
//
//    ImageWithConstraints(
//        modifier = modifier,
//        imageBitmap = imageBitmap,
//        contentDescription = null,
//        contentScale = contentScale
//    ) {
//        val imageWidth = this.imageWidth
//        val imageHeight = this.imageHeight
//
//        Drawing(
//            this.imageHeight, this.imageWidth,
//            modifier = Modifier.size(imageWidth, imageHeight),
//            onBase64ImageUpdate // Pass the callback to Drawing
//        )
//    }
//}
//
//@Composable
//private fun Drawing(
//    modifier_height: Dp,
//    modifier_width: Dp,
//    modifier: Modifier,
//    onBase64ImageUpdate: (String) -> Unit // Callback to pass the base64Image
//) {
//    var motionEvent by remember { mutableStateOf(MotionEvent.Idle) }
//    var currentPosition by remember { mutableStateOf(Offset.Unspecified) }
//    var previousPosition by remember { mutableStateOf(Offset.Unspecified) }
//    var base64Image by remember { mutableStateOf("") }
//
//    // State for clearing the drawing
//    var isCleared by remember { mutableStateOf(false) }
//
//    val transition: InfiniteTransition = rememberInfiniteTransition()
//    val phase by transition.animateFloat(
//        initialValue = .3f,
//        targetValue = .9f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 1, easing = FastOutLinearInEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//
//    val color = Color.Red
//
//    val paint = remember {
//        Paint().apply {
//            style = PaintingStyle.Stroke
//            strokeWidth = 21f
//            strokeCap = StrokeCap.Butt
//
//            this.asFrameworkPaint().apply {
//                val transparent = color.copy(alpha = 0f).toArgb()
//                this.color = transparent
//            }
//        }
//    }
//
//    paint.asFrameworkPaint().setShadowLayer(
//        3f * phase,
//        0f,
//        0f,
//        color.copy(alpha = phase).toArgb()
//    )
//
//    val path = remember(modifier) { Path() }
//
//    val drawModifier = modifier
//        .clipToBounds()
//        .pointerMotionEvents(
//            onDown = { pointerInputChange: PointerInputChange ->
//                currentPosition = pointerInputChange.position
//                motionEvent = MotionEvent.Down
//                pointerInputChange.consume()
//            },
//            onMove = { pointerInputChange: PointerInputChange ->
//                currentPosition = pointerInputChange.position
//                motionEvent = MotionEvent.Move
//                pointerInputChange.consume()
//            },
//            onUp = { pointerInputChange: PointerInputChange ->
//                motionEvent = MotionEvent.Up
//                pointerInputChange.consume()
//            },
//            delayAfterDownInMillis = 25L
//        )
//
//    val my_width = modifier_width.dpToPx()
//    val my_height = modifier_height.dpToPx()
//    val bitmap = remember { Bitmap.createBitmap(my_width, my_height, Bitmap.Config.ARGB_8888) }
//
//    val bitmapCanvas = remember { android.graphics.Canvas(bitmap) }
//
//    Canvas(modifier = drawModifier) {
//        when (motionEvent) {
//            MotionEvent.Down -> {
//                path.moveTo(currentPosition.x, currentPosition.y)
//                previousPosition = currentPosition
//            }
//
//            MotionEvent.Move -> {
//                path.quadraticBezierTo(
//                    previousPosition.x,
//                    previousPosition.y,
//                    (previousPosition.x + currentPosition.x) / 2,
//                    (previousPosition.y + currentPosition.y) / 2
//                )
//                previousPosition = currentPosition
//            }
//
//            MotionEvent.Up -> {
//                path.lineTo(currentPosition.x, currentPosition.y)
//                currentPosition = Offset.Unspecified
//                previousPosition = currentPosition
//                motionEvent = MotionEvent.Idle
//            }
//
//            else -> Unit
//        }
//
//        this.drawIntoCanvas {
//            it.drawPath(path, paint)
//
//            drawPath(
//                color = Color.White.copy((0.4f + phase).coerceAtMost(1f)),
//                path = path,
//                style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
//            )
//
//            val androidPath = path.asAndroidPath()
//            bitmapCanvas.drawPath(androidPath, paint.asFrameworkPaint())
//
//            // Update base64Image after each draw operation
//            base64Image = bitmap.toBase64()
//            onBase64ImageUpdate(base64Image) // Call the callback to update base64Image
//        }
//    }
//
//    // Clear drawing functionality
//    if (isCleared) {
//        path.reset()
//        base64Image = bitmap.toBase64()
//        onBase64ImageUpdate(base64Image)
//        isCleared = false
//    }
//
//    // UI for clearing the drawing
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        contentAlignment = Alignment.TopEnd
//    ) {
//        IconButton(
//            onClick = { isCleared = true },
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.rr), // Use your clear icon resource
//                contentDescription = "Clear Drawing",
//            )
//        }
//    }
//}
//
//
//@Composable
//fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx().toInt() }
//// Helper function to convert bitmap to Base64
//fun Bitmap.toBase64(): String {
//    val baos = ByteArrayOutputStream()
//    compress(Bitmap.CompressFormat.PNG, 100, baos)
//    val bytes = baos.toByteArray()
//    return Base64.encodeToString(bytes, Base64.DEFAULT)
//}
//
//
//
//
////...................API CALL..............................///////////////////////
//
//fun bitmapToBase68(bitmap: Bitmap): String {
//    val byteArrayOutputStream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//    val byteArray = byteArrayOutputStream.toByteArray()
//    return Base64.encodeToString(byteArray, Base64.DEFAULT)
//}
//
//
//
