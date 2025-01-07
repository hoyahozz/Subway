import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.library")
}

android {
    setNamespace("domain")
}

dependencies {
    implementation(libs.inject)
}
