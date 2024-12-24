package co.kr.hoyahozz.subway

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "co.kr.hoyaho.subway.$name"
    }
}
