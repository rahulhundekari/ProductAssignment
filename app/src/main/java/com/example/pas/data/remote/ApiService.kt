package com.example.pas.data.remote

import com.example.pas.data.dto.ProductDto
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProducts() : ProductDto
}