package com.example.pas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pas.domain.model.Product
import com.example.pas.presentation.home_screen.HomeScreen
import com.example.pas.presentation.home_screen.HomeViewModel
import com.example.pas.presentation.detail_screen.DetailsScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

        composable(
            route = Screens.HomeScreen.route
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val homeScreenState by viewModel.state.collectAsState()
            HomeScreen(navController, viewModel::onEvent, homeScreenState)
        }

        composable(
            route = Screens.DetailsScreen.route
        ) {
            val product =
                navController.previousBackStackEntry?.savedStateHandle?.get<Product>("productData")

            if (product != null) {
                DetailsScreen(navController, product)
            }
        }
    }

}