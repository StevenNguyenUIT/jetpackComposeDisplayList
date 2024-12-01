package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nhinhnguyenuit.jetpackcomposedisplaylist.R
import com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.navigation.Screen

@Composable
fun ListScreen(navController: NavController, viewModel: ListViewModel = hiltViewModel()) {
    val items by viewModel.items.collectAsState()
    val sortBy by viewModel.sortBy.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadItems()
    }

    Column {
        SortMenu(modifier = Modifier.padding(8.dp),
            selectedSort = sortBy,
            onSortedChange = {
                viewModel.updateSortBy(it)
            })
        LazyColumn {
            items(items) { item ->
                ListItem(item = item,
                    onClick = { navController.navigate(Screen.DetailScreen.createRoute(item.index)) })
            }
        }
    }

}

@Composable
fun SortMenu(
    modifier: Modifier = Modifier,
    selectedSort: SortBy,
    onSortedChange: (SortBy) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        TextButton(onClick = { expanded = true }) {
            Text(text = "Sort by: $selectedSort")
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = stringResource(id = R.string.sort))
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            SortBy.entries.forEach { sort ->
                DropdownMenuItem(text = {
                    Text(
                        text = "Sort by ${sort.name}",
                        fontWeight = if (sort == selectedSort) FontWeight.Bold else FontWeight.Normal
                    )
                }, onClick = {
                    expanded = false
                    onSortedChange(sort)
                })
            }
        }
    }

}