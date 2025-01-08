package co.kr.hoyaho.data.service

import co.kr.hoyaho.data.model.StationsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface SubwayService {
    @GET("SearchSTNBySubwayLineInfo/{startIndex}/{endIndex}/")
    suspend fun getStations(
        @Path("startIndex") startIndex: Int = 0,
        @Path("endIndex") endIndex: Int = 999,
    ): StationsResponse
}
