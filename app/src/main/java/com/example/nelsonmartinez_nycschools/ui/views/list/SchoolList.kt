package com.example.nelsonmartinez_nycschools.ui.views.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nelsonmartinez_nycschools.data.models.Schools
import com.example.nelsonmartinez_nycschools.data.models.SchoolsItem

@Composable
fun SchoolsList(schools: Schools?, schoolClick: (SchoolsItem) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        content = {
            schools?.let {
                items(items = it) { school ->
                    SchoolsItem(item = school) {
                        schoolClick(school)
                    }
                }
            }
        }
    )
}