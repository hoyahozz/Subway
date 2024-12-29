import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.library")
    id("subway.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    setNamespace("core.navigation")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
