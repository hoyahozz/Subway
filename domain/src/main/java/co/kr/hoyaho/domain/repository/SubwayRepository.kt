package co.kr.hoyaho.domain.repository

import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.domain.model.StationPassengerStats

interface SubwayRepository {
    suspend fun getStations(): Result<List<Station>>
    suspend fun getRecentStationPassengerStats(
        lineNumber: String,
        stationName: String,
    ): Result<StationPassengerStats>
}
