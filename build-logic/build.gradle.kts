plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "subway.android.hilt"
            implementationClass = "co.kr.hoyahozz.subway.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "subway.kotlin.hilt"
            implementationClass = "co.kr.hoyahozz.subway.HiltKotlinPlugin"
        }
    }
}
