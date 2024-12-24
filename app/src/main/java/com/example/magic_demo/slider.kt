//package com.example.magic_demo
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.core.FastOutSlowInEasing
//import androidx.compose.animation.core.InfiniteTransition
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.imageResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.magic_demo.beforeafter.BeforeAfterImage
//import com.example.magic_demo.beforeafter.ContentOrder
//import kotlin.math.roundToInt
//
//
/////-----------------------------------------------------------------------------------------------
//
//class sliderActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//                Surface {
//                    Column(
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        BeforeAfterImageDemo()
//                    }
//                }
//        }
//    }
//}
//
//@Composable
//fun BeforeAfterImageDemo() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//        val imageBefore = ImageBitmap.imageResource(
//            LocalContext.current.resources, R.drawable.image_before_after_shoes_a
//        )
//
//        val imageAfter = ImageBitmap.imageResource(
//            LocalContext.current.resources, R.drawable.image_before_after_shoes_b
//        )
//
//        var contentScale by remember { mutableStateOf(ContentScale.FillBounds) }
//
//        Spacer(modifier = Modifier.height(40.dp))
//
//        BeforeAfterImage(
//            modifier = Modifier
//                .shadow(1.dp, RoundedCornerShape(10.dp))
//                .fillMaxWidth()
//                .aspectRatio(4 / 3f),
//            beforeImage = imageBefore,
//            afterImage = imageAfter,
//            contentOrder = ContentOrder.AfterBefore,
//            contentScale = contentScale
//        )
//    }
//}
