package com.example.magic_demo.TestActivities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.magic_demo.ImageSelectionScreen
import com.example.magic_demo.R
import com.example.magic_demo.Start_Screen
import com.example.magic_demo.beforeafter.BeforeAfterImage
import com.example.magic_demo.beforeafter.ContentOrder
import com.example.magic_demo.demo.ImageWithConstraints
import com.example.magic_demo.demo.MotionEvent
import com.example.magic_demo.demo.pointerMotionEvents
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit



var is_true_Up = false
var globalBitmap_Up: Bitmap? = null
var originalBitmap_Up: Bitmap? = null // Variable to hold the original image
var processedBitmap_Up: Bitmap? = null // Variable to hold the processed image
/////-----------------------------------------------------------------------------------------------
class UpTest : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Get the image URI passed from the previous activity
            val imageUri = intent.getStringExtra("imageUri")?.let { Uri.parse(it) }

            globalBitmap_Up = null
            originalBitmap_Up = null
            processedBitmap_Up = null
            is_true_Up = false

            Up(imageUri.toString()) // Pass the image URI to your composable

        }
    }
}

@Composable
@SuppressLint("UnusedBoxWithConstraintsScope")
@RequiresApi(Build.VERSION_CODES.P)
fun Up(uri: String) {
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var base64String by remember { mutableStateOf("") }
//    var base64Image by remember { mutableStateOf("") }
    var imageSize by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    var processedBitmap_Up by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) } // Add state for loading
    var isImageSaved by remember { mutableStateOf(false) }

    // Load the Bitmap asynchronously
    LaunchedEffect(uri) {
        try {
            val parsedUri = Uri.parse(uri)
            val source = ImageDecoder.createSource(context.contentResolver, parsedUri)
            val bitmap = ImageDecoder.decodeBitmap(source)
            imageBitmap = bitmap.asImageBitmap()
            base64String = bitmapToBase64__Up(bitmap)
            imageSize = Pair(bitmap.width, bitmap.height)
        } catch (e: Exception) {
            Log.e("RemovalActivity", "Error decoding image from URI: ${e.message}")
        }
    }

    fun secondApiCall(requestId: String, client: OkHttpClient, callback: (String) -> Unit){
        val getRequest = Request.Builder()
            .url("https://api.runpod.ai/v2/eu9kux29uyt2sd/status/$requestId") // Assuming requestId is part of the URL
            .get()
            .addHeader("Authorization", "Bearer rpa_T7VEM6L2WDFNOKGYYJDG7LO2PUK678FKAO5YMQ4Xgzj47v")
            .build()

        client.newCall(getRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("APIResponse", "Failed to make GET/ request", e)
            }

            override fun onResponse(call: Call, response: Response) {
                //Log.d("GET API Response", "GET Res: $response")

                response.body?.string()?.let { getResponseBody ->
                    //Log.d("GET API Response", "GET Response: $getResponseBody")
                    var newobj = JSONObject(getResponseBody)
                    val requeststatus = newobj .getString("status")

                    if(requeststatus == "COMPLETED"){

                        val requestoutput = newobj.optString("output", null)  // null if the "output" key is missing
                        if (requestoutput != null && requestoutput.isNotEmpty()) {
                            Log.d("APIResponse", "GET Response logged: $requestoutput")
                            val imageBytes = Base64.decode(requestoutput, Base64.DEFAULT)
                            val processedBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                            processedBitmap_Up = processedBitmap
                            isLoading = false // Stop loading animation
                        } else {
                            Log.d("APIResponse", "Output is missing or empty.")


                            // Show toast or alert dialog on the main thread
                            Handler(Looper.getMainLooper()).post {
                                // Show a toast message
                                Toast.makeText(context, "Image too large.", Toast.LENGTH_SHORT).show()

                                // Optional: Show an alert dialog
                                AlertDialog.Builder(context)
                                    .setTitle("Error")
                                    .setMessage("Image Size too large. Try with different one.")
                                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                                    .show()
                            }

                            isLoading = false
                        }

                    }
                    callback(requeststatus)


                }

                response.close()
            }
        })
    }
    fun firstApiCall(
        base64String: String
    ) {
        isLoading = true // Start loading animation
        val mainHandler = Handler(Looper.getMainLooper())

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()

        val jsonData = JSONObject().apply {
            put("input", JSONObject().apply {
                put("image", base64String)  // Nested the image string under "input"
            })

        }

        val requestBody = jsonData.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("https://api.runpod.ai/v2/eu9kux29uyt2sd/run")  // Your API endpoint
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer rpa_T7VEM6L2WDFNOKGYYJDG7LO2PUK678FKAO5YMQ4Xgzj47v")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("APIResponse", "Failed to send API call", e)
                isLoading = false // Stop loading animation
            }

            override fun onResponse(call: Call, response: Response) {

                // Check if response body is null or empty
                if (response.body == null) {
                    Log.e("APIResponse", "Response body is null")
                    response.close()
                    return
                }

                // Log the full response body for debugging
                response.body?.string()?.let { responseBody ->
                    //Log.d("API Response", "Response: $responseBody")

                    try {
                        var newobj = JSONObject(responseBody)
                        val requestId = newobj.getString("id") // Extract the requestId

                        mainHandler.post(object : Runnable {
                            override fun run() {
                                // Call your third API call function
                                secondApiCall(requestId, client) { status ->

                                    if (status == "COMPLETED") {
                                        // Stop the task if the status is completed
                                        Log.i("APIResponse", "Parsing response $status")
                                        mainHandler.removeCallbacks(this)  // Remove the task from the handler
                                    } else {
                                        Log.i("APIResponse", "Parsing response $status")
                                        mainHandler.postDelayed(this, 2000)
                                    }
                                }
                            }
                        })




                    }

                    catch (e: Exception) {
                        Log.e("APIResponse", "Error parsing response", e)
                    }
                    catch (e: JSONException) {
                        Log.e("APIResponse", "Error parsing JSON response", e)
                    } catch (e: Exception) {
                        Log.e("APIResponse", "Unexpected error", e)
                    }
                }
                response.close()

            }
        })
    }




    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(
                        Color.Black
//                        brush = Brush.linearGradient(
//                            colors = listOf(
//                                Color(0xFFFF6E7F),
//                                Color(0xFF536DFE),
//                            )
//                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
//
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Conditionally display BeforeAfterImageDemo
                        if (processedBitmap_Up != null) {
                            BeforeAfterImageDem0_Up(
                                beforeImage = imageBitmap,
                                afterImage = processedBitmap_Up?.asImageBitmap()
                            )
                        } else {
                            // Show original image
                            imageBitmap?.let {
                                ImageScale_Up(imageBitmap = it, base64String) { updatedBase64Image ->
//                                base64Image = updatedBase64Image
                                }
                            }
                        }
                    }

                    // Show loading animation
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                                .wrapContentSize(Alignment.Center)
                        ) {
                            // Column to hold the loading text and animated dots
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                // Loading text
                                Text(
                                    text = "",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                // Animated dots
                                AnimatedDots()
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp), // Add horizontal padding
                verticalArrangement = Arrangement.spacedBy(5.dp) // Add spacing between items
            ) {
                if (processedBitmap_Up == null) {
                    Button(
                        onClick = {
                            if (base64String.isNotEmpty() && imageSize != null) {
                                firstApiCall(
                                    base64String
                                )
                            } else {
                                Log.d("RemovalActivity", "Data is missing for API call")
                            }
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFC107)
                        ),
                        modifier = Modifier
                            .width(250.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "Proceed",
                            color = androidx.compose.ui.graphics.Color.Black,
                        )
//                    Spacer(modifier = Modifier.weight(1.1f))
//                    Icon(
//                        painter = painterResource(id = R.drawable.proceed),
//                        contentDescription = null,
//                        tint = androidx.compose.ui.graphics.Color.Unspecified,
//                        modifier = Modifier.size(40.dp)
//                    )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Row for Save Icon Button and Additional Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (processedBitmap_Up!== null) {
                        // Save Image Icon Button
                        IconButton(
                            onClick = {
                                processedBitmap_Up?.let { bitmap ->
                                    val contentValues = ContentValues().apply {
                                        put(
                                            MediaStore.Images.Media.DISPLAY_NAME,
                                            "image_${System.currentTimeMillis()}.png"
                                        )
                                        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                                    }

                                    val resolver = context.contentResolver
                                    val uri = resolver.insert(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        contentValues
                                    )

                                    uri?.let { imageUri ->
                                        resolver.openOutputStream(imageUri).use { outputStream ->
                                            if (outputStream != null) {
                                                bitmap.compress(
                                                    Bitmap.CompressFormat.PNG,
                                                    100,
                                                    outputStream
                                                )
                                            }
                                        }

                                        Toast.makeText(
                                            context,
                                            "Image saved successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        processedBitmap_Up = null
                                    }
                                }
                            },
                            enabled = !isImageSaved,
                            modifier = Modifier.size(55.dp) // Set size for the icon button
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ssave),
                                contentDescription = "Save Image",
                                tint = androidx.compose.ui.graphics.Color.Unspecified,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        // Add another button parallel to the save button
                        Button(
                            onClick = {
                                val intent = Intent(context, Start_Screen::class.java)
                                context.startActivity(intent)
                            },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFC107)
                            ),
                            modifier = Modifier
                                .height(40.dp)
                                .width(85.dp)
//                            .weight(1f) // Distribute space in the row
                        ) {
                            Text(
                                "Done",
                                color = androidx.compose.ui.graphics.Color.Black,
                            )
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun BeforeAfterImageDem0_Up(beforeImage: ImageBitmap?, afterImage: ImageBitmap?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var beforeContentScale by remember { mutableStateOf(ContentScale.Fit) }
        var afterContentScale by remember { mutableStateOf(ContentScale.Fit) }

        Spacer(modifier = Modifier.height(40.dp))

        if (beforeImage != null && afterImage != null) {
            BeforeAfterImage(
                modifier = Modifier
                    .shadow(1.dp, RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .aspectRatio(4 / 5f),
                beforeImage = beforeImage,
                afterImage = afterImage,
                contentOrder = ContentOrder.AfterBefore,
                beforeContentScale = beforeContentScale,
                afterContentScale = afterContentScale
            )
        }
    }
}

@Composable
fun ImageScale_Up(
    imageBitmap: ImageBitmap,
    base64String: String,
    onBase64ImageUpdate: (String) -> Unit // Callback to update the base64Image from Drawing
) {
    Text(
        text = "Up-Scale your Image",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.padding(18.dp)
    )
    var contentScale by remember { mutableStateOf(ContentScale.Fit) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val modifier = Modifier
//            .background(Color.LightGray)
//            .border(2.dp, Color.Red)
            .fillMaxWidth()
            .aspectRatio(4 / 5f)

        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}




//...................API CALL..............................///////////////////////

fun bitmapToBase64__Up(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen_Up() {
    Up(
        uri = String.toString()
    )
}
