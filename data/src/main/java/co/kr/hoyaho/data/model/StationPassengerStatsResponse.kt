package co.kr.hoyaho.data.model

import co.kr.hoyaho.domain.model.StationPassengerStats
import co.kr.hoyaho.domain.util.DateFormatter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StationPassengerStatsResponse(
    @SerialName("CardSubwayStatsNew")
    val result: CardSubwayStatsNew,
)

@Serializable
internal data class CardSubwayStatsNew(
    @SerialName("list_total_count")
    val listTotalCount: Int,
    @SerialName("row")
    val stats: List<PassengerStatsResponse>,
)

@Serializable
internal data class PassengerStatsResponse(
    @SerialName("SBWY_ROUT_LN_NM")
    val lineNumber: String,
    @SerialName("SBWY_STNS_NM")
    val stationName: String,
    @SerialName("GTOFF_TNOPE")
    val getOnNumberOfPassenger: Double,
    @SerialName("GTON_TNOPE")
    val getOffNumberOfPassenger: Double,
    @SerialName("REG_YMD")
    val registerDate: String,
    @SerialName("USE_YMD")
    val useDate: String,
)

internal fun StationPassengerStatsResponse.toDomain(
    dateFormatter: DateFormatter,
): List<StationPassengerStats> = result.toDomain(dateFormatter)

internal fun CardSubwayStatsNew.toDomain(
    dateFormatter: DateFormatter,
): List<StationPassengerStats> = stats.map { it.toDomain(dateFormatter) }

internal fun PassengerStatsResponse.toDomain(
    dateFormatter: DateFormatter,
): StationPassengerStats = StationPassengerStats(
    lineNumber = this.lineNumber,
    name = this.stationName,
    useDate = dateFormatter.parse(this.useDate),
    boardingCount = getOnNumberOfPassenger.toInt(),
    alightingCount = getOffNumberOfPassenger.toInt(),
)
