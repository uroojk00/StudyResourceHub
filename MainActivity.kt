package com.example.studyresourcehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyresourcehub.ui.theme.StudyResourceHubTheme

// ------------------- MainActivity -------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyResourceHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf("login") }

                    when (currentScreen) {
                        "login" -> LoginPage(
                            onLoginSuccess = { currentScreen = "home" },
                            onSignUpClick = { currentScreen = "signup" }
                        )
                        "signup" -> SignUpPage(
                            onSignUpSuccess = { currentScreen = "home" }
                        )
                        "home" -> MainEntrancePage(
                            onViewResourcesClick = { currentScreen = "resources" }
                        )
                        "resources" -> ResourceListPage(
                            resources = sampleResources,
                            onBackClick = { currentScreen = "home" }
                        )
                    }
                }
            }
        }
    }
}

// ------------------- Gradient Background -------------------
@Composable
fun GradientBackground(colors: List<Color>, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors))
    ) {
        content()
    }
}

// ------------------- Login Page -------------------
@Composable
fun LoginPage(onLoginSuccess: () -> Unit, onSignUpClick: () -> Unit) {
    GradientBackground(colors = listOf(Color(0xFF4FACFE), Color(0xFF00F2FE))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Study Resource Hub",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username or Email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(5.dp, RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(5.dp, RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onLoginSuccess() },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Login", color = Color(0xFF4FACFE), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onSignUpClick) {
                Text("Don't have an account? Sign Up", color = Color.White)
            }
        }
    }
}

// ------------------- Sign Up Page -------------------
@Composable
fun SignUpPage(onSignUpSuccess: () -> Unit) {
    GradientBackground(colors = listOf(Color(0xFF43E97B), Color(0xFF38F9D7))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(5.dp, RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(5.dp, RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(5.dp, RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onSignUpSuccess() },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Sign Up", color = Color(0xFF43E97B), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    }
}

// ------------------- Home / Entrance Page -------------------
@Composable
fun MainEntrancePage(onViewResourcesClick: () -> Unit) {
    GradientBackground(colors = listOf(Color(0xFF8360C3), Color(0xFF2EBF91))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Study Resource Hub",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onViewResourcesClick,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("View Resources", color = Color(0xFF8360C3), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Add resource page later */ },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.8f))
            ) {
                Text("Add Resource", color = Color(0xFF8360C3), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

// ------------------- Resource List Page with Back Button -------------------
@Composable
fun ResourceListPage(resources: List<StudyResource>, onBackClick: () -> Unit) {
    GradientBackground(colors = listOf(Color(0xFFFFAFBD), Color(0xFFFFC3A0))) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Back button
            TextButton(
                onClick = onBackClick,
                modifier = Modifier.padding(12.dp)
            ) {
                Text("< Back", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(resources) { resource ->
                    ResourceCard(
                        title = resource.title,
                        description = resource.description,
                        author = resource.author
                    )
                }
            }
        }
    }
}

// ------------------- Resource Card -------------------
@Composable
fun ResourceCard(title: String, description: String, author: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
            Spacer(modifier = Modifier.height(4.dp))
            Text(description, fontSize = 14.sp, color = Color(0xFF555555))
            Spacer(modifier = Modifier.height(8.dp))
            Text("By: $author", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// ------------------- Data Class -------------------
data class StudyResource(
    val title: String,
    val description: String,
    val author: String
)

// ------------------- Sample Data -------------------
val sampleResources = listOf(
    StudyResource("Data Structures Notes", "Detailed notes on arrays, lists, trees", "Alice"),
    StudyResource("DBMS Book PDF", "Comprehensive DBMS textbook", "Bob"),
    StudyResource("Operating Systems Summary", "Quick OS revision notes", "Charlie"),
    StudyResource("Networking Guide", "Complete guide for computer networks", "David"),
    StudyResource("Python Programming", "Python basics and advanced topics", "Eve")
)

// ------------------- Previews -------------------
@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() { StudyResourceHubTheme { LoginPage({}, {}) } }

@Preview(showBackground = true)
@Composable
fun PreviewSignUpPage() { StudyResourceHubTheme { SignUpPage({}) } }

@Preview(showBackground = true)
@Composable
fun PreviewEntrancePage() { StudyResourceHubTheme { MainEntrancePage({}) } }

@Preview(showBackground = true)
@Composable
fun PreviewResourceListPage() { StudyResourceHubTheme { ResourceListPage(sampleResources, {}) } }
