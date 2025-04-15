plugins {
    id("sunrating.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.davidmendozamartinez.framework.location"
}

dependencies {
    api(project(":data"))

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.play.services.location)

    kapt(libs.hilt.android.compiler)
}
