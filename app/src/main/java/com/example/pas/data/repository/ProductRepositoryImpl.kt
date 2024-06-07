package com.example.pas.data.repository

import com.example.pas.data.dto.toProducts
import com.example.pas.data.remote.ApiService
import com.example.pas.domain.repository.ProductRepository
import com.example.pas.domain.model.Product
import com.example.pas.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {
    override suspend fun getProducts(): Flow<Resources<List<Product>>> {
        return flow {

            emit(Resources.Loading(true))

            val productResponse = try {
                apiService.getProducts()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
                null
            }

            if (productResponse == null) {
                emit(Resources.Loading(false))
            }

            productResponse?.let { response ->
                emit(Resources.Success(data = response.toProducts()))
                emit(Resources.Loading(false))
            }
        }
    }
}