package com.example.magic_demo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


                LoginScreen()


        }
    }
}


// LOGIN SCREEN
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    val auth = FirebaseAuth.getInstance()
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black
//                brush = Brush.linearGradient(
//                    colors = listOf(
//                        Color(0xFF536DFE),
//                        Color(0xFFFF6E7F)
//                    )
//                )
            )

    ) {

        // Row for icons and logo at the top of the screen
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp)
        ) {
            // Left icon
            Icon(
                painter = painterResource(id = R.drawable.group),
                contentDescription = "Left Icon",
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Right icon
            Icon(
                painter = painterResource(id = R.drawable.magic),
                contentDescription = "Right Icon",
                modifier = Modifier.size(120.dp),
                tint = Color.Unspecified
            )
        }

        // Column for the rest of the login content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            // Sign In text
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Description text
            Text(
                text = "Officiis vero est. Velit sed voluptate. Quaerat aut est.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Email TextField with custom text and container color
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    textColor = Color.Black, // Change the text color inside the field
                    focusedBorderColor = Color.White, // Change the focused border color
                    unfocusedBorderColor = Color.DarkGray, // Change the unfocused border color
                    focusedLabelColor = Color.White, // Change the label color when focused
                    unfocusedLabelColor = Color.DarkGray, // Change the label color when unfocused
                    cursorColor = Color.White // Change cursor color
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField with custom text and container color
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    textColor = Color.Black, // Change the text color inside the field
                    focusedBorderColor = Color.White, // Change the focused border color
                    unfocusedBorderColor = Color.DarkGray, // Change the unfocused border color
                    focusedLabelColor = Color.White, // Change the label color when focused
                    unfocusedLabelColor = Color.DarkGray, // Change the label color when unfocused
                    cursorColor = Color(0xFF3F51B5) // Change cursor color
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(email.text, password.text)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(context, Start_Screen::class.java)
                                context.startActivity(intent)

                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, task.exception?.localizedMessage ?: "Account Not Found!", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign up button
            TextButton(
                onClick = {
                    val intent = Intent(context, SignupActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),

            ) {
                Text(text = "Sign up")
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen_Uppp() {
    LoginScreen()
}





// SOCIAL LOGIN SCREEN
/*

@Composable
fun SocialLoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background image at the top
        Image(
            painter = painterResource(id = R.drawable.bbb), // Replace with your background image
            contentDescription = "Background",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Adjust height as needed
        )

        // Row for icons at the top
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp) // Adjust padding for alignment
        ) {
            // Left icon
            Icon(
                painter = painterResource(id = R.drawable.group),
                contentDescription = "Left Icon",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Right icon
            Icon(
                painter = painterResource(id = R.drawable.magic),
                contentDescription = "Right Icon",
                modifier = Modifier.size(120.dp)
            )
        }

        // Close button at the top-left
        IconButton(
            onClick = { */
/* Handle close *//*
 },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close"
            )
        }

        // Main content section in Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(modifier = Modifier.height(220.dp))

            // Login Magic text
            Text(
                text = "Login Magic",
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Description text
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tincidunt sapien placerat.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Social Media Buttons
            SocialButton(
                text = "Continue with Facebook",
                icon = painterResource(id = R.drawable.fb), // Replace with your Facebook icon
                onClick = { */
/* Handle Facebook login *//*
 }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SocialButton(
                text = "Continue with Google",
                icon = painterResource(id = R.drawable.gg), // Replace with your Google icon
                onClick = { */
/* Handle Google login *//*
 }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SocialButton(
                text = "Continue with Dribbble",
                icon = painterResource(id = R.drawable.db), // Replace with your Dribbble icon
                onClick = { */
/* Handle Dribbble login *//*
 }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Arrow down icon
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "More options",
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Terms and Privacy Policy
            Text(
                text = "By signing up, I have read and agree to Intitled UI’s Terms and Privacy Policy",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 108.dp)
            )
        }
    }
}

@Composable
fun SocialButton(text: String, icon: Painter, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

*/



/*

@Composable
fun MagicMembershipScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background gradient
        Image(
            painter = painterResource(id = R.drawable.bbb), // Replace with your background image
            contentDescription = "Background",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Adjust height as needed
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Close icon at the top left
            IconButton(
                onClick = { */
/* TODO: Handle close *//*
 },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.x), // Replace with your close icon
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row for logos
            // Row for icons at the top
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp) // Adjust padding for alignment
        ) {
            // Left icon
            Icon(
                painter = painterResource(id = R.drawable.group),
                contentDescription = "Left Icon",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Right icon
            Icon(
                painter = painterResource(id = R.drawable.magic),
                contentDescription = "Right Icon",
                modifier = Modifier.size(120.dp)
            )
        }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Magic\nMembership",
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp,
                textAlign = TextAlign.Left,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                lineHeight = 52.sp  // Set line height for desired spacing
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Features
            val features = listOf(
                "300+ Preset Available",
                "Video Editing",
                "Exclusive Tutorial Editing",
                "Photo Challenges"
            )

            features.forEach { feature ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.t), // Replace with your checkmark icon
                        contentDescription = "Check",
                        tint = Color(0xFF00C9A7)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = feature,
                        fontSize = 16.sp,
                        color = Color(0xFF00C9A7),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Free Trial Highlighted Text
            Text(
                text = "Start 1 Week Free Trial",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF00C9A7),
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(110.dp))

            // Paid Subscription Button
            Button(
                onClick = { */
/* TODO: Handle subscription info *//*
 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0F7FA)
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .defaultMinSize(minHeight = 40.dp)
            ) {
                Text(
                    text = "For Paid Subscriptions",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Start Free Trial Button
            Button(
                onClick = { */
/* TODO: Handle start trial *//*
 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFADD8E6)
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .defaultMinSize(minHeight = 50.dp)
            ) {
                Text(text = "Start Free Trial", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Try it Later Button
            OutlinedButton(
                onClick = { */
/* TODO: Handle try later *//*
 },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .defaultMinSize(minHeight = 50.dp)
            ) {
                Text(text = "Try it Later")
            }
        }
    }
}

*/
