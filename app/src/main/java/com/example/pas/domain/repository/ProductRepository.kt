package com.example.pas.domain.repository

import com.example.pas.domain.model.Product
import com.example.pas.utils.Resources
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts() : Flow<Resources<List<Product>>>

}