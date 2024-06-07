package com.example.pas.data.dto

import com.example.pas.domain.model.Product

data class ProductDto(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
) {
    data class Product(
        val availabilityStatus: String,
        val brand: String,
        val category: String,
        val description: String,
        val dimensions: Dimensions,
        val discountPercentage: Double,
        val id: Int,
        val images: List<String>,
        val meta: Meta,
        val minimumOrderQuantity: Int,
        val price: Double,
        val rating: Double,
        val returnPolicy: String,
        val reviews: List<Review>,
        val shippingInformation: String,
        val sku: String,
        val stock: Int,
        val tags: List<String>,
        val thumbnail: String,
        val title: String,
        val warrantyInformation: String,
        val weight: Int
    ) {
        data class Dimensions(
            val depth: Double,
            val height: Double,
            val width: Double
        )

        data class Meta(
            val barcode: String,
            val createdAt: String,
            val qrCode: String,
            val updatedAt: String
        )

        data class Review(
            val comment: String,
            val date: String,
            val rating: Int,
            val reviewerEmail: String,
            val reviewerName: String
        )
    }
}

fun ProductDto.toProducts(): List<Product> {
    return products.map {
        Product(
            title = it.title,
            category = it.category,
            description = it.description,
            discountPercentage = it.discountPercentage,
            id = it.id,
            images = it.images,
            thumbnail = it.thumbnail,
            price = it.price,
            rating = it.rating
        )
    }
}