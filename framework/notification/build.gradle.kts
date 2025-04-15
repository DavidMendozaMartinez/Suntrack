plugins {
    id("sunrating.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.davidmendozamartinez.framework.notification"
}

dependencies {
    api(project(":data"))

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.datetime)

    kapt(libs.hilt.android.compiler)
}
