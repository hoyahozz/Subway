package co.kr.hoyaho.data.repository

import co.kr.hoyaho.data.model.StationsResponse
import co.kr.hoyaho.data.model.toDomain
import co.kr.hoyaho.data.source.SubwayDataSource
import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.domain.model.StationPassengerStats
import co.kr.hoyaho.domain.repository.SubwayRepository
import co.kr.hoyaho.domain.util.DateFormatter
import javax.inject.Inject

internal class SubwayRepositoryImpl @Inject constructor(
    private val subwayDataSource: SubwayDataSource,
    private val dateFormatter: DateFormatter,
) : SubwayRepository {
    override suspend fun getStations(): Result<List<Station>> =
        subwayDataSource.getStations().map(StationsResponse::toDomain)

    override suspend fun getRecentStationPassengerStats(
        lineNumber: String,
        stationName: String,
    ): Result<StationPassengerStats> =
        subwayDataSource.getRecentStationPassengerStats(
            lineNumber = lineNumber,
            stationName = stationName,
        )
            .map { it.toDomain(dateFormatter) }
            .map { it.first() }
}
