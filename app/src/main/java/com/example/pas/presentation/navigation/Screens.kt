package com.example.pas.presentation.navigation

import com.example.pas.presentation.navigation.ScreenConstants.DETAILS_SCREEN
import com.example.pas.presentation.navigation.ScreenConstants.HOME_SCREEN

sealed class Screens(val route: String) {

    data object HomeScreen : Screens(HOME_SCREEN)
    data object DetailsScreen : Screens(DETAILS_SCREEN)

    fun withArg(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}