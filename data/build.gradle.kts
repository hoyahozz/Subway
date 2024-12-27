import co.kr.hoyahozz.subway.setNamespace
import java.util.Properties

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").bufferedReader())

plugins {
    id("subway.android.library")
    id("subway.android.hilt")
    id("kotlinx-serialization")
}

android {
    setNamespace("data")

    defaultConfig {
        buildConfigField("String", "BASE_API_URL", "${localProperties["BASE_API_URL"]}")
        buildConfigField("String", "API_KEY", "${localProperties["API_KEY"]}")
    }

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
