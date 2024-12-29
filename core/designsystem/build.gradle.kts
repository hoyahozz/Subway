import co.kr.hoyahozz.subway.setNamespace

plugins {
    id("subway.android.library")
    id("subway.android.compose")
}

android {
    setNamespace("core.designsystem")
}

dependencies {
    implementation(libs.androidx.appcompat)
}
