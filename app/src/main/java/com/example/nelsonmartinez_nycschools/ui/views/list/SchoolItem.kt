package com.example.nelsonmartinez_nycschools.ui.views.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nelsonmartinez_nycschools.data.models.SchoolsItem

@Composable
fun SchoolsItem(item: SchoolsItem, onCLick: (SchoolsItem) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.schoolName ?: "NO AVAILABLE",
            modifier = Modifier.padding(8.dp).clickable {
                onCLick(item)
            }
        )
    }
}

