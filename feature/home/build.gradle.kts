import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.feature")
}

android {
    setNamespace("feature.home")
}

dependencies {
    implementation(libs.kotlinx.immutable)
}
