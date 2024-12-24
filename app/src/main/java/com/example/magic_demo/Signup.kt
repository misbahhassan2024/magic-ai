package com.example.magic_demo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


                SignupScreen()


        }
    }
}


// SIGN UP SCREEN
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen() {
    val auth = FirebaseAuth.getInstance()
    var email by remember { mutableStateOf(TextFieldValue("")) }
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
                .padding(top = 45.dp) // Adjust padding as needed to position below the system bar
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
            Spacer(modifier = Modifier.height(100.dp)) // Add space below the icons

            // Sign In text
            Text(
                text = "Sign up",
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

            // Email TextField
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

            // Password TextField
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
                        Icon(imageVector = image, "")
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
                    cursorColor = Color.White // Change cursor color
                )
            )



            Spacer(modifier = Modifier.height(24.dp))

            // Login Button
            Button(
                onClick = { auth.createUserWithEmailAndPassword(email.text, password.text)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign up success, handle navigation or state update
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)

                            Log.d("app", "login")
                            Toast.makeText(context, "Sign up successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            // Handle error
                            Toast.makeText(context, task.exception?.localizedMessage ?: "Sign up failed!", Toast.LENGTH_SHORT).show()

                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Sign up")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign up button
            TextButton(
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            ) {
                Text(text = "Login")
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewImageSelectionScreen_Upppppp() {
    SignupScreen()
}
