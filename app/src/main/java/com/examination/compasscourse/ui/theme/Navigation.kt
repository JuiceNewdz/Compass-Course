package com.examination.compasscourse.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.examination.compasscourse.model.AuthViewModel

import com.examination.compasscourse.view.LoginView
import com.examination.compasscourse.view.ProgramOffersView
import com.examination.compasscourse.view.AdminView
import com.examination.compasscourse.view.OverView
import com.examination.compasscourse.view.AdmissionTestView
import com.examination.compasscourse.view.GetStartedView

@Composable
fun Navigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    // Initialize the NavController
    val navController = rememberNavController()

    // Set up NavHost with a start destination
    NavHost(navController = navController, startDestination = Route.GetStartedView){
        // Define each destination
        composable(Route.GetStartedView) {
            GetStartedView(modifier, navController, authViewModel)
        }
        // Add other screens as needed, e.g., "home", "details", etc.
        composable(Route.ProgramOffers) {
            ProgramOffersView(modifier, navController, authViewModel)
        }
        composable(Route.TestView) {
            AdmissionTestView(modifier, navController, authViewModel)
        }
        composable(Route.AdminView) {
            AdminView(modifier, navController, authViewModel, isAdmin = true)
        }
        composable(Route.OverView) {
            OverView(navController,authViewModel)
        }
        composable(Route.LoginView) {
            LoginView(modifier, navController, authViewModel)
        }
    }

}

// Sample composable screen
