package com.example.pas.presentation.home_screen

import com.example.pas.domain.model.Product

data class HomeScreenState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorText: String? = null
)