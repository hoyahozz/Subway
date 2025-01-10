package co.kr.hoyaho.domain.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateFormatter @Inject constructor() {
    fun parse(
        rawDate: String,
        formatter: DateTimeFormatter = DATE_FORMAT,
    ): LocalDate {
        require(rawDate.isNotBlank())

        return LocalDate.parse(rawDate, formatter)
    }

    fun format(
        date: LocalDate,
        formatter: DateTimeFormatter = DATE_FORMAT,
    ): String = formatter.format(date)

    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd")
    }
}
