package com.examination.compasscourse.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examination.compasscourse.model.AuthViewModel
import com.examination.compasscourse.ui.theme.Route

@Composable
fun OverView(navController: NavController,authViewModel: AuthViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent // Optional: Set to transparent if you have a gradient
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Gradient background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF008EFF),  // Blue
                                Color(0xFFF3D63C)   // Yellow
                            )
                        )
                    )
            )

            // Background Image with a faded effect
            Image(
                painter = painterResource(id = com.examination.compasscourse.R.drawable.bisu), // Replace with your image resource
                contentDescription = "BISU Logo",
                modifier = Modifier
                    .fillMaxSize() // Make the image fill the entire box
                    .graphicsLayer(alpha = 0.3f), // Faded effect for image
                contentScale = ContentScale.Crop // Scale the image to crop it if needed
            )

            // Main content (Text and Information)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize() // Ensure the column fills the box
            ) {
                // Title text
                Text(
                    text = "WELCOME!!!",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(top = 32.dp) // Adjust padding for better spacing
                )

                // Description text inside a box
                Box(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .background(Color.LightGray.copy(alpha = 0.7f)) // Light background for contrast
                        .padding(16.dp) // Padding around the text
                ) {
                    Text(
                        text = "Compass Course is a data-driven app designed for aspiring BISU students. It analyzes admission test scores across numeric, logic, verbal, and analytical areas to recommend the most suitable degree programs. Using advanced algorithms, the app provides personalized course suggestions based on each student's strengths and career goals, ensuring a perfect academic match. With an intuitive interface and real-time insights, Compass Course helps students make informed decisions about their academic future.",
                        color = Color.Black,
                        fontSize = 18.sp, // Adjusted font size for readability
                        fontStyle = FontStyle.Normal, // Changed to normal for description text
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    IconButton(
                        onClick = {
                            // Handle click action for the regular user
                            navController.navigate(Route.ProgramOffers)
                        },
                        modifier = Modifier
                            .size(56.dp)
                            .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Next",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}
