package com.example.pas.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pas.domain.repository.ProductRepository
import com.example.pas.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

//    var state by mutableStateOf(HomeScreenState())
//        private set

    private var _state = MutableStateFlow(HomeScreenState())
    var state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            HomeScreenEvents.LoadProducts -> {
                loadProducts()
            }
        }
    }

    fun loadProducts() {
        viewModelScope.launch {
            productRepository.getProducts().collect { result ->
                when (result) {
                    is Resources.Loading -> {
                        _state.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resources.Success -> {
                        result.data?.let { listings ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    products = listings,
                                    errorText = null
                                )
                            }
                        }
                    }

                    is Resources.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorText = result.message ?: "Unknown Error"
                            )
                        }
                    }
                }
            }
        }
    }
}