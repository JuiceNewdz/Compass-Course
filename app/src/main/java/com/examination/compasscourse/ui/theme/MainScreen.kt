package com.examination.compasscourse.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val navItemList = listOf(
        NavItem("home", Icons.Default.Home)
    )


    Scaffold(
        Modifier.fillMaxSize(),
        floatingActionButton = {
           navItemList.forEachIndexed { index, navItem ->
               NavigationRailItem(
                   selected = true ,
                   onClick = {  },
                   icon = {
                          Icon(imageVector  = navItem.icon, contentDescription = "Icon")
                          },
                   label = {
                       Text(text = navItem.label)
                   }
               )
           }
        }
    ){innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding))

    }

}


@Composable
fun ContentScreen(modifier: Modifier){

}