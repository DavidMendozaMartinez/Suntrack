import java.util.Properties

plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.davidmendozamartinez.framework.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        val localProperties: File = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(localProperties.inputStream())

        val sunsethueApiKey: String = properties.getProperty("SUNSETHUE_API_KEY") ?: ""
        buildConfigField(type = "String", name = "SUNSETHUE_API_KEY", value = sunsethueApiKey)
    }
}

dependencies {
    api(project(":data"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
}