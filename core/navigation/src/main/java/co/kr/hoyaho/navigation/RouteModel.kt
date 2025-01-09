package co.kr.hoyaho.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data class Detail(
        val lineNumber: String,
        val stationName: String,
    ) : Route
}
