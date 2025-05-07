plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.sunrating.framework.schedule"
}

dependencies {
    api(project(":data"))
    api(libs.work.runtime.ktx)
    api(libs.hilt.work)

    implementation(project(":common"))
    implementation(libs.androidx.core.ktx)

    kapt(libs.hilt.compiler)
}
