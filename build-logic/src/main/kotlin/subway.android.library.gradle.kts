import co.kr.hoyahozz.subway.configureCoroutineAndroid
import co.kr.hoyahozz.subway.configureHiltAndroid
import co.kr.hoyahozz.subway.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()
