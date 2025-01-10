package co.kr.hoyaho.domain.usecase

import co.kr.hoyaho.domain.model.StationPassengerStats
import co.kr.hoyaho.domain.repository.SubwayRepository
import javax.inject.Inject

class GetRecentStationPassengerStatsUseCase @Inject constructor(
    private val repository: SubwayRepository,
) {
    suspend operator fun invoke(
        lineNumber: String,
        stationName: String,
    ): Result<StationPassengerStats> =
        repository.getRecentStationPassengerStats(
            lineNumber = lineNumber,
            stationName = stationName,
        )
}
