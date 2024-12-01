package com.nhinhnguyenuit.jetpackcomposedisplaylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomain
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list.ListItem
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.navigation.AppNavGraph
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.theme.JetpackComposeDisplayListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeDisplayListTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    JetpackComposeDisplayListTheme {
        val item = ItemDomain(
            44,
            "Ethics in Technology",
            "2023-07-20",
            "Explore the ethical considerations in technology and their implications."
        )
        ListItem(item =item) {
        }
    }
}