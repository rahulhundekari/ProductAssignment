package com.example.pas.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pas.R
import com.example.pas.domain.model.Product
import com.example.pas.presentation.components.ProductItem
import com.example.pas.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onEvent: (HomeScreenEvents) -> Unit,
    state: HomeScreenState
) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { innerPadding ->

        if (state.errorText == null) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Products",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(state.products) {
                        ProductItem(product = it) {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "productData",
                                value = it
                            )
                            navController.navigate(Screens.DetailsScreen.route)
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = state.errorText,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }


    }

}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
        onEvent = {},
        state = HomeScreenState(
            products = listOf(
                Product(
                    title = "Product title",
                    category = "Category",
                    description = "Description",
                    discountPercentage = 10.00,
                    id = 1,
                    images = listOf("", ""),
                    price = 20.00,
                    rating = 4.5,
                    thumbnail = ""
                )
            )
        )
    )
}

