package co.kr.hoyaho.domain.model

import java.time.LocalDate

data class StationPassengerStats(
    val lineNumber: String,
    val name: String,
    val useDate: LocalDate,
    val boardingCount: Int,
    val alightingCount: Int,
)
