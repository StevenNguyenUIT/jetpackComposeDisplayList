package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nhinhnguyenuit.jetpackcomposedisplaylist.R

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
    index: Int
) {
    val item by viewModel.item.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var isDeleted by remember { mutableStateOf(false) } // status after deletion

    LaunchedEffect(Unit) {
        viewModel.loadItem(index)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
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
            item?.let {
                Text(
                    text = "Description: ${it.description}", style = TextStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            item?.let {
                Text(
                    text = "Date: ${it.date}",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(id = R.string.delete))
            }
        } else {
            AlertDialog(
                onDismissRequest = {},
                title = { stringResource(id = R.string.success_title) },
                text = { Text(stringResource(id = R.string.success_message)) },
                confirmButton = {
                    Button(
                        onClick = {
                            isDeleted = false
                            navController.popBackStack()
                        }
                    ) {
                        Text(stringResource(id = R.string.ok))
                    }
                }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = stringResource(id = R.string.confirm_deletion)) },
                text = { Text(stringResource(id = R.string.delete_confirmation)) },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteItem(item)
                            showDialog = false
                            isDeleted = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(stringResource(id = R.string.delete), color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            )
        }
    }
}