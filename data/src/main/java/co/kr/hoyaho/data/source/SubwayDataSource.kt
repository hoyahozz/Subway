package co.kr.hoyaho.data.source

import co.kr.hoyaho.data.model.StationsResponse
import co.kr.hoyaho.data.service.SubwayService
import javax.inject.Inject

internal class SubwayDataSource @Inject constructor(
    private val subwayService: SubwayService,
) {
    suspend fun getStations(): Result<StationsResponse> = runCatching {
        subwayService.getStations()
    }
}
