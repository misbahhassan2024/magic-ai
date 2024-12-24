//package com.example.magic_demo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/*

class NextScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NextScreen()
        }
    }

    @SuppressLint("NotConstructor")
    @Composable
    fun NextScreen() {
        val context = LocalContext.current

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.background), // Replace with your image resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Bottom Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Add padding to prevent buttons from touching the screen edges
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                // Button 1 with Icon
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, BackgroundRemoverActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB2EBF2)
                        ),
                        modifier = Modifier.weight(1f) // Makes the button take equal width
                    ) {
                        Text(
                            "Background Remover",
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Space between button and icon
                    Icon(
                        painter = painterResource(id = R.drawable.bg), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp) // Adjust the size as needed
                    )
                }

                // Button 2 with Icon
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, ObjectRemoverActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB2EBF2)
                        ),
                        modifier = Modifier.weight(1f) // Makes the button take equal width
                    ) {
                        Text(
                            "Object Remover",
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Space between button and icon
                    Icon(
                        painter = painterResource(id = R.drawable.obg), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp) // Adjust the size as needed
                    )
                }

                // Button 3 with Icon
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, EnhancerActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB2EBF2)
                        ),
                        modifier = Modifier.weight(1f) // Makes the button take equal width
                    ) {
                        Text(
                            "Up-Scale",
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Space between button and icon
                    Icon(
                        painter = painterResource(id = R.drawable.enhance), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp) // Adjust the size as needed
                    )
                }
                // Button 4 with Icon
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, RestoreActivity::class.java)
                            context.startActivity(intent)
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB2EBF2)
                        ),
                        modifier = Modifier.weight(1f) // Makes the button take equal width
                    ) {
                        Text(
                            "Enhance",
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Space between button and icon
                    Icon(
                        painter = painterResource(id = R.drawable.restore), // Replace with your icon resource
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp) // Adjust the size as needed
                    )
                } // Button 4 with Icon
//                Row(
//                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                ) {
//                    Button(
//                        onClick = {
//                            val intent = Intent(context, sliderActivity ::class.java)
//                            context.startActivity(intent)
//                        },
//                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFB2EBF2)
//                        ),
//                        modifier = Modifier.weight(1f) // Makes the button take equal width
//                    ) {
//                        Text(
//                            "EEEE",
//                            color = Color.Black,
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(8.dp)) // Space between button and icon
//                    Icon(
//                        painter = painterResource(id = R.drawable.restore), // Replace with your icon resource
//                        contentDescription = null,
//                        tint = Color.Unspecified,
//                        modifier = Modifier.size(40.dp) // Adjust the size as needed
//                    )
//                }
            }
        }
    }
}

*/
