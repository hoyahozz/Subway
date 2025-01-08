package co.kr.hoyaho.data.repository

import co.kr.hoyaho.data.model.StationsResponse
import co.kr.hoyaho.data.model.toDomain
import co.kr.hoyaho.data.source.SubwayDataSource
import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.domain.repository.SubwayRepository
import javax.inject.Inject

internal class SubwayRepositoryImpl @Inject constructor(
    private val subwayDataSource: SubwayDataSource,
) : SubwayRepository {
    override suspend fun getStations(): Result<List<Station>> =
        subwayDataSource.getStations().map(StationsResponse::toDomain)
}
