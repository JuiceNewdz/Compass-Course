package com.examination.compasscourse.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examination.compasscourse.R
import com.examination.compasscourse.ui.theme.Route
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import com.examination.compasscourse.model.AuthViewModel

@Composable
fun GetStartedView(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF008CFC) // Sky blue color
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF008EFF), Color(0xFFF3D63C)) // Sky blue to yellow
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.offset(y = (-100).dp)
                ) {
                    // Logo
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(250.dp)
                    )

                    // Motto Texts
                    Text(
                        text = "Unlock best BISU path,",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Cursive,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "One Course at a Time.",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Cursive,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    // Centered "Continue as Admin" Button below the motto
                    Button(
                        onClick = {
                            // Handle admin navigation here
                            navController.navigate(Route.LoginView)
                        },
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape),
                        colors = ButtonDefaults.buttonColors(Color.White)
                    ) {
                        Text("Continue as Admin", color = Color.Black, fontSize = 12.sp)
                    }
                }
            }

            // BISU Logo and campus information
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bisu),
                    contentDescription = "BISU Logo",
                    modifier = Modifier
                        .size(70.dp)
                        .offset(x = 10.dp, y = (-10).dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 80.dp, bottom = 20.dp)
                        .offset(y = -30.dp)
                ) {
                    Text("BISU - Clarin Campus", fontSize = 12.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraLight)
                    Text("Since 2009", fontSize = 12.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraLight)
                    Text("Pub.Norte, Clarin, Bohol", fontSize = 12.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraLight)
                }
            }

            // "Next" Button on the bottom right
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {
                        // Handle click action for the regular user
                        navController.navigate(Route.OverView)
                    },
                    modifier = Modifier
                        .padding(16.dp)
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

