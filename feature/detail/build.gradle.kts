import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.feature")
}

android {
    setNamespace("feature.detail")
}

dependencies {
    implementation(libs.kotlinx.immutable)
}
