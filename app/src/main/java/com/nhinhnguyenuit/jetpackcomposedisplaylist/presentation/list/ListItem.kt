package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomainModel

@Composable
fun ListItem(item: ItemDomainModel, onClick: ()->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Index: ${item.index}")
            Text(text = "Title: ${item.title}")
            Text(text = "Date: ${item.date}")
        }
    }
}