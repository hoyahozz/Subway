package co.kr.hoyaho.data.service

import co.kr.hoyaho.data.model.StationPassengerStatsResponse
import co.kr.hoyaho.data.model.StationsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface SubwayService {
    @GET("SearchSTNBySubwayLineInfo/{startIndex}/{endIndex}/")
    suspend fun getStations(
        @Path("startIndex") startIndex: Int = 0,
        @Path("endIndex") endIndex: Int = 999,
    ): StationsResponse

    @GET("CardSubwayStatsNew/{startIndex}/{endIndex}/{useYmd}/{sbwyRoutLnNm}/{sbwyStnsNm}")
    suspend fun getStationPassengerStats(
        @Path("startIndex") startIndex: Int = 0,
        @Path("endIndex") endIndex: Int = 999,
        @Path("useYmd") useDate: String,
        @Path("sbwyRoutLnNm") lineNumber: String,
        @Path("sbwyStnsNm") stationName: String,
    ): StationPassengerStatsResponse
}
