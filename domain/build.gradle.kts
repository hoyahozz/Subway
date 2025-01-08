plugins {
    id("subway.kotlin.library")
    id("subway.kotlin.hilt")
}

dependencies {
    implementation(libs.inject)
    implementation(libs.coroutines.core)
}
