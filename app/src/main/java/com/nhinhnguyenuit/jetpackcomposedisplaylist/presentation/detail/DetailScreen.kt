package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
    itemId: Int
) {
    val item by viewModel.item.collectAsState()

    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.loadItem(itemId)
    }

    var isDeleted by remember { mutableStateOf(false) } // status after deletion

    Column(modifier = Modifier.padding(16.dp)) {
        if (!isDeleted) {
            item?.let { Text(text = it.title, style = MaterialTheme.typography.titleLarge) }
            Spacer(modifier = Modifier.height(8.dp))
            item?.let { Text(text = it.description, style = MaterialTheme.typography.bodyMedium) }
            Spacer(modifier = Modifier.height(8.dp))
            item?.let { Text(text = it.date, style = MaterialTheme.typography.labelLarge) }
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Delete")
            }
        }

        if (isDeleted) {
            // Hiển thị Popup thông báo thành công
            AlertDialog(
                onDismissRequest = {},
                title = { Text(text = "Success") },
                text = { Text("Item deleted successfully!") },
                confirmButton = {
                    Button(
                        onClick = {
                            isDeleted = false
                            navController.popBackStack()
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }


        // Dialog xác nhận xóa
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Confirm Deletion")
                },
                text = {
                    Text("Are you sure you want to delete this item?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteItem(item)
//                            navController.popBackStack()
                            showDialog = false
                            isDeleted = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}