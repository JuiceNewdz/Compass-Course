package com.examination.compasscourse.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examination.compasscourse.data.AdmissionTestdata
import com.examination.compasscourse.model.AuthViewModel
import com.examination.compasscourse.ui.theme.CompassCourseTheme

@Composable
fun AdminView(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel, isAdmin: Boolean) {
    CompassCourseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
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
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // Make the entire interface scrollable
                ) {
                    // Return button at the top
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp), // Add padding below the button
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Return",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Admission Test",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }

                    // Test names and lock state
                    val tests = AdmissionTestdata
                    var completedTests by remember { mutableStateOf(0) }

                    // Display each test card with additional spacing
                    tests.forEachIndexed { index, test ->
                        var isLocked = index > completedTests
                        TestCard(
                            testName = test.title,
                            isLocked = isLocked,
                            onTestCompleted = {
                                completedTests = index + 1
                            },
                            isAdmin = isAdmin,
                            onTestNameChange = { newName ->
                                tests[index].title = newName
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp) // Reduce vertical padding
                        )
                    }

                    // Spacer to push the content up so that the button stays at the bottom
                    Spacer(modifier = Modifier.height(100.dp))
                }

                // Submit button at the bottom right
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Button(
                        onClick = { /* Handle confirm action */ },
                        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestCard(
    testName: String,
    isLocked: Boolean,
    onTestCompleted: () -> Unit,
    isAdmin: Boolean,
    onTestNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var editableTestName by remember { mutableStateOf(testName) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFCC00) // Yellow color for the card background
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp), // Use CardDefaults.elevation for the correct type
        modifier = modifier
            .height(100.dp)
            .clickable(enabled = !isLocked) { onTestCompleted() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lock or unlock icon based on the test status
            Icon(
                imageVector = if (isLocked) Icons.Filled.Lock else Icons.Filled.HourglassEmpty,
                contentDescription = if (isLocked) "Locked" else "Unlocked",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                if (isAdmin) {
                    TextField(
                        value = editableTestName,
                        onValueChange = {
                            editableTestName = it
                            onTestNameChange(it)
                        },
                        label = { Text("Edit Test Name") },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Text(text = testName, fontSize = 18.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Progress: 0%", fontSize = 14.sp, color = Color.Black)
                Text(text = "Score:", fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}
