package co.kr.hoyaho.data.model

import co.kr.hoyaho.domain.model.Station
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StationsResponse(
    @SerialName("SearchSTNBySubwayLineInfo")
    val result: SearchSTNBySubwayLineInfo,
)

@Serializable
internal data class SearchSTNBySubwayLineInfo(
    @SerialName("list_total_count")
    val totalCount: Int,
    @SerialName("row")
    val stations: List<StationResponse>,
)

@Serializable
internal data class StationResponse(
    @SerialName("FR_CODE")
    val frCode: String,
    @SerialName("LINE_NUM")
    val lineNum: String,
    @SerialName("STATION_CD")
    val stationCode: String,
    @SerialName("STATION_NM")
    val stationName: String,
    @SerialName("STATION_NM_CHN")
    val stationNameChn: String,
    @SerialName("STATION_NM_ENG")
    val stationNameEng: String,
    @SerialName("STATION_NM_JPN")
    val stationNameJpn: String,
)

internal fun StationsResponse.toDomain(): List<Station> = result.toDomain()

internal fun SearchSTNBySubwayLineInfo.toDomain(): List<Station> = stations.map(StationResponse::toDomain)

internal fun StationResponse.toDomain(): Station = Station(
    id = frCode,
    name = stationName,
    lineNumber = lineNum,
)
