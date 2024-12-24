package com.example.magic_demo.TestActivities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

/*

class ProcessedImageActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Get the processed Bitmap Base64 string from the intent
            val processedBitmapBase64 = intent.getStringExtra("processedBitmapBase64")
            val processedBitmap = processedBitmapBase64?.let { base64ToBitmap(it) }

            processedBitmap?.let {
                // Display the processed image
                ProcessedImageScreen(it)
            }
        }
    }

    // Helper function to convert Base64 string back to Bitmap
    fun base64ToBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}

@Composable
fun ProcessedImageScreen(
    bitmap: Bitmap
) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "Processed Image", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Image(
//            bitmap = bitmap.asImageBitmap(),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(1f)
//                .border(2.dp, Color.Gray)
//        )
//    }

    var contentScale by remember { mutableStateOf(ContentScale.Fit) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val modifier = Modifier
            .background(Color.LightGray)
            .border(2.dp, Color.Red)
            .fillMaxWidth()
            .aspectRatio(4 / 6f)

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}


*/



class NextScreen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val viewModel: SharedViewModel = viewModel()
            NextScreen(viewModel)
        }
    }
}

@Composable
fun NextScreen(viewModel: SharedViewModel) {
    val processedBitmap = viewModel.processedBitmap.value

    processedBitmap?.let {
        val imageBitmap = it.asImageBitmap()
        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
