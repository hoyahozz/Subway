import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.library")
    id("subway.android.hilt")
    id("kotlinx-serialization")
}

android {
    setNamespace("data")

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.immutable)
}
