package com.example.nelsonmartinez_nycschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nelsonmartinez_nycschools.ui.theme.NelsonMartinezNYCSchoolsTheme
import com.example.nelsonmartinez_nycschools.ui.views.details.SchoolDetails
import com.example.nelsonmartinez_nycschools.ui.views.list.SchoolListScreen
import com.example.nelsonmartinez_nycschools.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SchoolViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NelsonMartinezNYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "schools") {
                        composable(route = "schools") {
                            SchoolListScreen(viewModel = viewModel, navController)
                        }

                        composable(route = "details") {
                            SchoolDetails(viewModel, navController)
                        }
                    }

                }
            }
        }
    }
}