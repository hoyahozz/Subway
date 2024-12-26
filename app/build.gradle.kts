plugins {
    id("subway.android.application")
    id("subway.android.compose")
    id("subway.verify.lint")
}

android {
    namespace = "co.kr.hoyaho.subway"

    defaultConfig {
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
}
