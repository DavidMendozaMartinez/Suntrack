plugins {
    id("sunrating.android.library")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.davidmendozamartinez.framework.database"
}

dependencies {
    api(project(":data"))

    implementation(libs.hilt.android)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)

    kapt(libs.hilt.android.compiler)

    ksp(libs.room.compiler)
}
