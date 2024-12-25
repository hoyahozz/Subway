import co.kr.hoyahozz.subway.configureKotlin
import co.kr.hoyahozz.subway.configureTest

plugins {
    kotlin("jvm")
    id("subway.kotlin.lint")
}

configureKotlin()
configureTest()
