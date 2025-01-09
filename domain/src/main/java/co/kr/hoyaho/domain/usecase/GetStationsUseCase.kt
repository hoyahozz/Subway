package co.kr.hoyaho.domain.usecase

import co.kr.hoyaho.domain.model.Station
import co.kr.hoyaho.domain.repository.SubwayRepository
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val subwayRepository: SubwayRepository,
) {
    suspend operator fun invoke(): Result<List<Station>> =
        subwayRepository.getStations()
}
