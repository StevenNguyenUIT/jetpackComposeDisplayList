package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.navigation

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail.DetailScreen
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list.ListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    val currentScreen = remember {
        mutableStateOf("list")
    }
    Scaffold(
        topBar = {
            when (currentScreen.value) {
                "list" -> CenterAlignedTopAppBar(
                    title = { Text(text = "Item List") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                "detail" -> CenterAlignedTopAppBar(title = { Text(text = "Item Details")},
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    })
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.padding(padding)
        ) {
            composable("list") {
                currentScreen.value = "list"
                ListScreen(navController = navController)
            }
            composable("detail/{itemId}") { backStackEntry ->
                val index = backStackEntry.arguments?.getString("itemId")?.toInt()
                currentScreen.value = "detail"
                DetailScreen(navController, itemId = index ?: 0)
            }
        }
    }

}