package com.example.magic_demo.TestActivities

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.DisposableEffect

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.magic_demo.R
import com.example.magic_demo.Start_Screen
import com.example.magic_demo.beforeafter.BeforeAfterImage
import com.example.magic_demo.beforeafter.ContentOrder
import com.example.magic_demo.bootom_navigation.Surface
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

var is_true_Fs = false
var globalBitmap_Fs: Bitmap? = null
var originalBitmap_Fs: Bitmap? = null // Variable to hold the original image
var processedBitmap_Fs: Bitmap? = null // Variable to hold the processed image
/////-----------------------------------------------------------------------------------------------
class FsTest : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Get the image URI passed from the previous activity
            val imageUri = intent.getStringExtra("imageUri")?.let { Uri.parse(it) }

            globalBitmap_Fs = null
            originalBitmap_Fs = null
            processedBitmap_Fs = null
            is_true_Fs = false

            Fs(imageUri.toString()) // Pass the image URI to your composable

        }
    }
}

@Composable
@SuppressLint("UnusedBoxWithConstraintsScope")
@RequiresApi(Build.VERSION_CODES.P)
fun Fs(uri: String) {
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var base64String by remember { mutableStateOf("") }
    var base64Image by remember { mutableStateOf("") }
    var imageSize by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    var processedBitmap_Fs by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) } // Add state for loading
    var isImageSaved by remember { mutableStateOf(false) }
    var selectedImageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    // Load the Bitmap asynchronously
    LaunchedEffect(uri) {
        try {
            val parsedUri = Uri.parse(uri)
            val source = ImageDecoder.createSource(context.contentResolver, parsedUri)
            val bitmap = ImageDecoder.decodeBitmap(source)
            imageBitmap = bitmap.asImageBitmap()
            base64String = bitmapToBase64__Fs(bitmap)

            imageSize = Pair(bitmap.width, bitmap.height)
        } catch (e: Exception) {
            Log.e("RemovalActivity", "Error decoding image from URI: ${e.message}")
        }
    }

    fun secondApiCall(requestId: String, client: OkHttpClient, callback: (String) -> Unit) {
        val getRequest = Request.Builder()
            .url("https://api.runpod.ai/v2/cy7zosldjalte6/status/$requestId") // Assuming requestId is part of the URL
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
                    val requeststatus = newobj.getString("status")

                    if (requeststatus == "COMPLETED") {

                        val requestoutput =
                            newobj.optString("output", null)  // null if the "output" key is missing
                        if (requestoutput != null && requestoutput.isNotEmpty()) {

                            val outputJson =
                                JSONObject(requestoutput) // Parse the "output" string into a JSON object
                            val base64Image = outputJson.optString("image", null)
                            Log.d("APIResponse", "base64Image: $base64Image")

                            val imageBytes = Base64.decode(base64Image, Base64.DEFAULT)
                            val processedBitmap =
                                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                            processedBitmap_Fs = processedBitmap
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
                                    .setMessage("Output is missing or empty. Please try again later.")
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

    fun firstApiCall(base64String: String, base64Image: String) {
        isLoading = true // Start loading animation
        val mainHandler = Handler(Looper.getMainLooper())

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()

        val SOURCE_INDEXES = "-1"
        val TARGET_INDEXES = "-1"
        val BACKGROUND_ENHANCE = true
        val FACE_RESTORE = true
        val FACE_UPSAMPLE = true
        val UPSCALE = 1
        val CODEFORMER_FIDELITY = 0.5
        val OUTPUT_FORMAT = "JPEG"

        val jsonData = JSONObject().apply {
            put("input", JSONObject().apply {
                put("target_image", base64Image)
                put("source_image", base64String)
                put("source_indexes", SOURCE_INDEXES)
                put("target_indexes", TARGET_INDEXES)
                put("background_enhance", BACKGROUND_ENHANCE)
                put("face_restore", FACE_RESTORE)
                put("face_upsample", FACE_UPSAMPLE)
                put("upscale", UPSCALE)
                put("codeformer_fidelity", CODEFORMER_FIDELITY)
                put("output_format", OUTPUT_FORMAT)
            })

        }

        val requestBody = jsonData.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("https://api.runpod.ai/v2/cy7zosldjalte6/run")  // Your API endpoint
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

                    } catch (e: Exception) {
                        Log.e("APIResponse", "Error parsing response", e)
                    } catch (e: JSONException) {
                        Log.e("APIResponse", "Error parsing JSON response", e)
                    } catch (e: Exception) {
                        Log.e("APIResponse", "Unexpected error", e)
                    }
                }
                response.close()

            }
        })
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            Log.d("Background", "Image URI selected: $uri")

            try {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                val bitmap = ImageDecoder.decodeBitmap(source)
                selectedImageBitmap = bitmap.asImageBitmap()

                // Convert selected bitmap to Base64
                base64Image = bitmapToBase64__Fs(bitmap)
                imageSize = Pair(bitmap.width, bitmap.height)  // Update the size
                Log.d("Background", "Bitmap created and converted to Base64")

            } catch (e: Exception) {
                Log.e("Background", "Error creating bitmap from URI: ${e.message}")
            }
        } else {
            Log.d("Background", "No image selected or operation canceled")
            Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Conditionally display original and reference images only if processedBitmap_Fs is null
                    if (processedBitmap_Fs == null) {
                        imageBitmap?.let {
                            Text(
                                text = "Original Image",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                            Image(
                                bitmap = it,
                                contentDescription = "Original Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4 / 3f)
                                    .padding(16.dp)
                            )
                        }

                        selectedImageBitmap?.let {
                            Spacer(modifier = Modifier.height(16.dp)) // Add some space
                            Text(
                                text = "Reference Image",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                            Image(
                                bitmap = it,
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4 / 2f)
                                    .padding(16.dp)
                            )
                        }
                    } else {
                        // Conditionally display BeforeAfterImageDemo when processedBitmap_Fs is available
                        BeforeAfterImageDem0_Fs(
                            beforeImage = selectedImageBitmap,
                            afterImage = processedBitmap_Fs?.asImageBitmap()
                        )
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
        },
        floatingActionButton = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (processedBitmap_Fs == null) {
                        Box(
                            modifier = Modifier
                                //.size(90.dp) // Adjust size as needed
                                .width(140.dp)
                                .height(50.dp)
                                .shadow(
                                    10.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    ambientColor = Color.Black
                                )
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
                                        // Click action for the button
                                        if (base64String.isNotEmpty() && base64Image.isNotEmpty() && imageSize != null) {
                                            firstApiCall(
                                                base64String,
                                                base64Image,
                                            )
                                        } else {
                                            Log.d("RemovalActivity", "Data is missing for API call")
                                        }
                                    }
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "Proceed",
                                        style = TextStyle(
                                            color = Black,
                                            fontSize = 20.sp,
//                                        fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        // Select Image Button
                        Button(
                            onClick = {
                                launcher.launch("image/*")
                            },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFC107)
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                        ) {
                            Text("Select Image", color = androidx.compose.ui.graphics.Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Row for Save Icon Button and Additional Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (processedBitmap_Fs!== null) {
                        // Save Image Icon Button
                        IconButton(
                            onClick = {
                                processedBitmap_Fs?.let { bitmap ->
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
                                        processedBitmap_Fs = null
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

                        // Done Button
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
                        ) {
                            Text("Done", color = androidx.compose.ui.graphics.Color.Black)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun BeforeAfterImageDem0_Fs(
    beforeImage: ImageBitmap?,
    afterImage: ImageBitmap?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
//            .verticalScroll(rememberScrollState())
    ) {
        var beforeContentScale by remember { mutableStateOf(ContentScale.Fit) }
        var afterContentScale by remember { mutableStateOf(ContentScale.Fit) }

        Spacer(modifier = Modifier.height(40.dp))

        if (beforeImage != null && afterImage != null) {
            BeforeAfterImage(
                modifier = Modifier
//                    .shadow(1.dp, RoundedCornerShape(10.dp))
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

fun bitmapToBase64__Fs(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen_Fs() {
    Fs(
        uri = String.toString()
    )
}
