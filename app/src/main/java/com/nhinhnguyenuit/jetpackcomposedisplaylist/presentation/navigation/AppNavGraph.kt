package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhinhnguyenuit.jetpackcomposedisplaylist.R
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail.DetailScreen
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list.ListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    val currentScreen = remember {
        mutableStateOf(Title.LIST.query)
    }
    Scaffold(
        topBar = {
            when (currentScreen.value) {
                Title.LIST.query -> CenterAlignedTopAppBar(
                    title = { Text(text = stringResource(id = R.string.list_screen_title)) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

                Title.DETAIL.query -> CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.detail_screen_title)) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    })
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.ListScreen.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.ListScreen.route) {
                currentScreen.value = Title.LIST.query
                ListScreen(navController = navController)
            }
            composable(Screen.DetailScreen.route) { backStackEntry ->
                val index = backStackEntry.arguments?.getString("index")?.toInt()?:0
                currentScreen.value = Title.DETAIL.query
                DetailScreen(navController, index = index)
            }
        }
    }
}

enum class Title(val query: String){
    LIST("list"),
    DETAIL("detail")
}

