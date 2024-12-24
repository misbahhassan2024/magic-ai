package com.example.magic_demo

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.magic_demo.TestActivities.Up

class WelcomeScreen : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            welcomeScreen()
        }
    }
}

@Composable
fun welcomeScreen() {

    val context = LocalContext.current
    // Background gradient
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black
//                brush = Brush.linearGradient(
//                        colors = listOf(
//                            Color(0xFF536DFE),
//                            Color(0xFFFF6E7F)
//                        )
//                    )
            )
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.sssp), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section (Logo and Title)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
//                Spacer(modifier = Modifier.height(50.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.magic), // Replace with your drawable logo
//                    contentDescription = "Fitness Club Logo",
//                    modifier = Modifier.size(80.dp),
//                    contentScale = ContentScale.Fit
//                )
                Spacer(modifier = Modifier.height(200.dp))

//                Text(
//                    text = "Turn your Moments\n"+"like Magic now!",
//                    fontSize = 34.sp,
//                    fontWeight = FontWeight.Bold,
//                    fontFamily = FontFamily.Monospace,
//                    color = Color.White
//                )
            }

            // Welcome Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Welcome Back!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Sign In Button
                Button(
                    onClick = {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(Color.DarkGray)
                ) {
                    Text(text = "LOGIN", color = Color(0xFFFFC107))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign Up Button
                Button(
                    onClick = {
                        val intent = Intent(context, SignupActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFC107))
                ) {
                    Text(text = "SIGN UP", color = Color.Black)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Sign In Button
                Button(
                    onClick = {
                        val intent = Intent(context, Start_Screen::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(text = "GUEST", color = Color.White)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen_Up() {
    welcomeScreen(
    )
}
