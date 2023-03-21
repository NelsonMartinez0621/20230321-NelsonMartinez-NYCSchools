package com.example.nelsonmartinez_nycschools.ui.views.list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nelsonmartinez_nycschools.utils.NetworkResult
import com.example.nelsonmartinez_nycschools.viewmodel.SchoolViewModel

@Composable
fun SchoolListScreen(viewModel: SchoolViewModel, navController: NavController) {
    val schools = viewModel.schoolList.observeAsState().value
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column() {
            schools?.let { result ->
                when(result) {
                    // Show loading circle
                    is NetworkResult.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                    is NetworkResult.Success -> {
                        // Load the list of schools
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Schools", style = MaterialTheme.typography.h4)

                            SchoolsList(schools = result.data) {
                                // When a school is selected, search the SAT scores for that school
                                // then navigate to the detail page
                                viewModel.selectedSchool = it
                                viewModel.searchScores()
                                navController.navigate("details")
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.d("TAG_X", result.message.toString())
                    }
                }
            }
        }
    }
}