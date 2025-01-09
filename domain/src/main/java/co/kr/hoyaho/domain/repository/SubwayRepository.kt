package co.kr.hoyaho.domain.repository

import co.kr.hoyaho.domain.model.Station

interface SubwayRepository {
    suspend fun getStations(): Result<List<Station>>
}
