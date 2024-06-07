package com.example.pas.di

import com.example.pas.data.repository.ProductRepositoryImpl
import com.example.pas.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

}