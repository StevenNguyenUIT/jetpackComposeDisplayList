package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.navigation

sealed class Screen(val route: String) {
    data object ListScreen: Screen("list_screen")
    data object DetailScreen: Screen("detail_screen/{index}"){
        fun createRoute(index: Int) = "detail_screen/$index"
    }
}