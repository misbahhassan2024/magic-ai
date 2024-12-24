package com.example.magic_demo


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.magic_demo.ui.theme.Magic_demoTheme
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Magic_demoTheme {
                GreetingScreen()
            }
        }
    }
}
    @SuppressLint("NotConstructor")
    @Composable
    fun GreetingScreen() {
        val context = LocalContext.current

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.sssp), // Replace with your image resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Top Row with Two Icons
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.group),
//                    contentDescription = "Icon 1",
//                    modifier = Modifier.size(40.dp)
//                )
//                Spacer(modifier = Modifier.width(8.dp)) // Space between the two icons
//                Image(
//                    painter = painterResource(id = R.drawable.magic),
//                    contentDescription = "Icon 2",
//                    modifier = Modifier.size(130.dp)
//                )
//            }

            // Bottom Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Add padding to prevent buttons from touching the screen edges
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Button 1 with Icon
                Button(
                    onClick = {
                        val intent = Intent(context, WelcomeScreen::class.java)
                        context.startActivity(intent)
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC107)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                ) {
                    Row(
//                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Start Now",
                            color = androidx.compose.ui.graphics.Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1.1f)) // Pushes the icon to the end
                        Icon(
                            painter = painterResource(id = R.drawable.vector), // Replace with your icon resource
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color.Unspecified,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen_Upp() {
    GreetingScreen(
    )
}
