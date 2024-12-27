import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.library")
}

android {
    setNamespace("domain")
}

dependencies {
    implementation(projects.data)
    implementation(libs.inject)
}
