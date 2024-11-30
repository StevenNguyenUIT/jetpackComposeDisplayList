package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.util.Locale

@Composable
fun ListScreen(navController: NavController, viewModel: ListViewModel = hiltViewModel()) {
    val items by viewModel.items.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadItems()
    }

    Column {
        SortMenu(modifier = Modifier.padding(8.dp),
            onSortSelected = { sortBy ->
                viewModel.setSortingCriteria(sortBy)
            })
        LazyColumn {
            items(items) { item ->
                ListItem(item = item,
                    onClick = { navController.navigate("detail/${item.index}") })
            }
        }
    }

}

@Composable
fun SortMenu(modifier: Modifier = Modifier, onSortSelected: (String) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var seletedOption by remember {
        mutableStateOf("index")
    }

    Box(modifier = modifier) {
        Button(onClick = { expanded = true }) {
            Text(text = "Sort by: $seletedOption")
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf("index", "title", "date").forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.capitalize(Locale.ROOT)) },
                    onClick = {
                        expanded = false
                        seletedOption = option
                        onSortSelected(option)
                    })
            }
        }
    }
}