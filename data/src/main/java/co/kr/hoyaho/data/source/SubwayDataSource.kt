package co.kr.hoyaho.data.source

import co.kr.hoyaho.data.model.StationPassengerStatsResponse
import co.kr.hoyaho.data.model.StationsResponse
import co.kr.hoyaho.data.service.SubwayService
import co.kr.hoyaho.domain.util.DateFormatter
import java.time.LocalDate
import javax.inject.Inject

internal class SubwayDataSource @Inject constructor(
    private val subwayService: SubwayService,
    private val dateFormatter: DateFormatter,
) {
    suspend fun getStations(): Result<StationsResponse> = runCatching {
        subwayService.getStations()
    }

    suspend fun getRecentStationPassengerStats(
        lineNumber: String,
        stationName: String,
    ): Result<StationPassengerStatsResponse> = runCatching {
        subwayService.getStationPassengerStats(
            // 서울열린데이터광장에서 제공하는 가장 최신 데이터는 4일전의 데이터이다.
            useDate = dateFormatter.format(LocalDate.now().minusDays(4)),
            lineNumber = lineNumber,
            stationName = stationName,
        )
    }
}
