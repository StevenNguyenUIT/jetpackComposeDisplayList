package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail.DetailScreen
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list.ListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list"){ ListScreen(navController = navController)}
        composable("detail/{itemId}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("itemId")?.toInt()
            DetailScreen(navController, itemId = index?:0)
        }
    }
}