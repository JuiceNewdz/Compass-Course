package com.examination.compasscourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.examination.compasscourse.model.AuthViewModel
import com.examination.compasscourse.ui.theme.Navigation
import com.examination.compasscourse.ui.theme.CompassCourseTheme
import androidx.compose.material3.ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()

        setContent {
            CompassCourseTheme {
                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    content = { innerPadding ->
                        Navigation(modifier = Modifier.padding(innerPadding), authViewModel = authViewModel
                        )
                    }
                )
            }
        }
    }
}
