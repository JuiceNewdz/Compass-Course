package com.examination.compasscourse.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examination.compasscourse.R
import com.examination.compasscourse.model.AuthViewModel
import com.examination.compasscourse.ui.theme.Route

data class FloatingButtonData(
    val iconResId: Int,
    val text: String,
    val backgroundColor: Color,  // Color for the button background
    val textColor: Color          // Color for the button text
)

@Composable
fun ProgramOffersView(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {
    var selectedProgram by remember { mutableStateOf<FloatingButtonData?>(null) }

    val buttons = listOf(
        FloatingButtonData(R.drawable.cs, "Bachelor of Science in Computer Science", Color(0xFFA00A0F), Color.White),
        FloatingButtonData(R.drawable.bisu, "Bachelor of Elementary Education", Color(0xFF05A8F3), Color.White),
        FloatingButtonData(R.drawable.bisu, "Bachelor of Science in Hospitality Management", Color(0xFF0AB9EE), Color.White),
        FloatingButtonData(R.drawable.bisu, "Bachelor of Science in Environmental Science", Color(0xFF06F026), Color.White),
        FloatingButtonData(R.drawable.bisu, "Bachelor in Secondary Education major in Mathematics", Color(0xFF0440F1), Color.White),
        FloatingButtonData(R.drawable.bisu, "Bachelor in Technology Education major in Home Economics", Color(0xFFFF5722), Color.White),
        FloatingButtonData(R.drawable.bisu, "Master of Arts in Education major in Education Management", Color(0xFF6200EE), Color.White)
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFF008CFC) // Sky blue color
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF008EFF),
                            Color(0xFFF3D63C)
                        ) // Sky blue to yellow
                    )
                )
        ) {
            // Background image
            Image(
                painter = painterResource(id = com.examination.compasscourse.R.drawable.bisu), // Replace with your image resource
                contentDescription = "BISU Logo",
                modifier = Modifier
                    .fillMaxSize() // Make the image fill the entire box
                    .graphicsLayer(alpha = 0.3f), // Faded effect for image
                contentScale = ContentScale.Crop // Scale the image to crop it if needed
            )

            if (selectedProgram == null) {
                // Main view with scrollable buttons
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    // Title at the top
                    Text(
                        text = "Bachelor's Programs Offered:",
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Start),
                        fontSize = 24.sp // Define font size directly
                    )

                    // Floating buttons row in the center
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp), // Vertical padding
                        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
                    ) {
                        items(buttons) { buttonData ->
                            FloatingButton(buttonData, onClick = {
                                selectedProgram = buttonData // Set selected program on click
                            })
                        }
                    }

                    // Bottom section with text and button in a row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, end = 16.dp)
                    ) {
                        // Instruction text aligned to the left of the Start button
                        Text(
                            text = "Press the button to start Admission Test",
                            color = Color.Black,
                            fontSize = 12.sp, // Smaller font size
                            modifier = Modifier.weight(1f) // Pushes the text to the left
                        )

                        // Start button
                        Button(
                            onClick = { navController.navigate(Route.TestView) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000)) // Yellow color
                        ) {
                            Text(text = "Next")
                        }
                    }
                }
            } else {
                // Show the detailed card view for the selected program
                DetailedProgramCard(selectedProgram!!) {
                    selectedProgram = null // Reset to show main view again
                }
            }
        }
    }
}

@Composable
fun FloatingButton(buttonData: FloatingButtonData, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(300.dp, 300.dp) // Increased size of the button
            .padding(8.dp), // Padding around buttons for better spacing on different screen sizes
        shape = RoundedCornerShape(16.dp), // Set rounded corners
        colors = ButtonDefaults.buttonColors(containerColor = buttonData.backgroundColor) // Use unique background color
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize() // Ensure the Column fills the available space
        ) {
            // Icon
            Image(
                painter = painterResource(id = buttonData.iconResId),
                contentDescription = buttonData.text,
                modifier = Modifier.size(60.dp) // Increased icon size
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Centered Text with customizable color
            Text(
                text = buttonData.text,
                modifier = Modifier.fillMaxWidth(), // Ensure text occupies full width to center it
                textAlign = androidx.compose.ui.text.style.TextAlign.Center, // Align text in the center horizontally
                color = buttonData.textColor // Apply the text color
            )
        }
    }
}

@Composable
fun DetailedProgramCard(program: FloatingButtonData, onBackClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(24.dp), // Increased padding for a larger card
            colors = CardDefaults.cardColors(containerColor = program.backgroundColor) // Use program's color
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp) // Increased internal padding
            ) {
                // Icon
                Image(
                    painter = painterResource(id = program.iconResId),
                    contentDescription = program.text,
                    modifier = Modifier.size(80.dp) // Increased icon size
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = program.text, color = Color.White, fontSize = 24.sp) // Larger program name
                Spacer(modifier = Modifier.height(16.dp))

                // Program Overview and Career Options based on the selected program
                when (program.text) {
                    "Bachelor of Science in Computer Science" -> {
                        Text(
                            text = "Program Overview:\nFocuses on software engineering, algorithms, and network design for solving real-world problems.",
                            color = Color.White,
                            fontSize = 18.sp // Increased font size
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Career options:\nSoftware Developer, Web Developer, Data Analyst, Systems Engineer, etc.",
                            color = Color.White,
                            fontSize = 18.sp // Increased font size
                        )
                    }
                    "Bachelor of Elementary Education" -> {
                        Text(
                            text = "Program Overview:\nEquips students with teaching skills for the elementary school level, emphasizing child development.",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Career options:\nElementary Teacher, School Administrator, Curriculum Designer, Education Consultant, etc.",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    "Bachelor of Science in Hospitality Management" -> {
                        Text(
                            text = "Program Overview:\nPrepares students for careers in hotel and restaurant management, event planning, and tourism.",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Career options:\nHotel Manager, Restaurant Owner, Event Coordinator, Tourism Consultant, etc.",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    else -> {
                        Text(
                            text = "Program Overview and Career options coming soon.",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Button color
                ) {
                    Text(text = "Back")
                }
            }
        }
    }
}
