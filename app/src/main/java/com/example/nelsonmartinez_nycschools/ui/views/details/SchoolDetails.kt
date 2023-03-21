package com.example.nelsonmartinez_nycschools.ui.views.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nelsonmartinez_nycschools.data.models.ScoresItem
import com.example.nelsonmartinez_nycschools.viewmodel.SchoolViewModel

@Composable
fun SchoolDetails(viewModel: SchoolViewModel, navController: NavController) {
    val scores = viewModel.scoresList.observeAsState().value
    val school = viewModel.selectedSchool

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column (Modifier.fillMaxSize()){
            Text(text = school?.schoolName ?: "N/A", modifier = Modifier.padding(vertical = 2.dp), style = MaterialTheme.typography.h5)
            Text(text = "Address: ${school?.primaryAddressLine1}, ${school?.city}, ${school?.stateCode}, ${school?.zip}", style = MaterialTheme.typography.subtitle2)
            Text(text = "Phone: ${school?.phoneNumber ?: "N/A"}", style = MaterialTheme.typography.subtitle2)
            Text(text = "Website: ${school?.website ?: "N/A"}", style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = "Number of test takers: ${scores?.data?.numOfSatTestTakers ?: "N/A"}", modifier = Modifier.padding(vertical = 2.dp))
            Text(text = "Average Scores:",  modifier = Modifier.padding(vertical = 2.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                TableCell(header = "Reading", score = scores?.data?.satCriticalReadingAvgScore ?: "N/A")
                TableCell(header = "Writing", score = scores?.data?.satWritingAvgScore ?: "N/A")
                TableCell(header = "Math", score = scores?.data?.satMathAvgScore ?: "N/A")
            }
            Text(text = school?.overviewParagraph ?: "Overview not available", modifier =Modifier.padding(4.dp))
        }
    }
}

@Composable
fun TableCell(header: String, score: String) {
    Column(
        modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = header)
        Text(text = score)
    }
}