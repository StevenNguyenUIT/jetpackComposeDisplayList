package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
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

    Column(modifier = Modifier
        .padding(16.dp)) {
        if (!isDeleted) {
            item?.let {
                Text(
                    text = it.title,
                    color = Color.Blue,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(
                        alignment = Alignment.CenterHorizontally
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            item?.let { Text(text = "Description: ${it.description}", style = TextStyle(
                fontStyle = FontStyle.Italic
            )) }
            Spacer(modifier = Modifier.height(8.dp))
            item?.let { Text(text = "Date: ${it.date}", style = MaterialTheme.typography.labelLarge) }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Delete")
            }
        } else {
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

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Deletion Confirmation")
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