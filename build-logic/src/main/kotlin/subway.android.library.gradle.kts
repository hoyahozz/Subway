import co.kr.hoyahozz.subway.configureCoroutineAndroid
import co.kr.hoyahozz.subway.configureHiltAndroid
import co.kr.hoyahozz.subway.configureKotlinAndroid
import co.kr.hoyahozz.subway.configureTest

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()
configureTest()
