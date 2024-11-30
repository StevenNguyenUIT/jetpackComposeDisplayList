package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
    itemId: Int
    ){
    val item by viewModel.item.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadItem(itemId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        item?.let { Text(text = it.title, style = MaterialTheme.typography.displayLarge) }
        item?.let { Text(text = it.description, style = MaterialTheme.typography.displayMedium) }
        item?.let { Text(text = it.date, style = MaterialTheme.typography.displaySmall) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.deleteItem(item)
            navController.popBackStack()
        }) {
            Text("Delete")
        }
    }
}