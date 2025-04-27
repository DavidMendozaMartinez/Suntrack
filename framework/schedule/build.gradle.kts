plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.sunrating.framework.schedule"
}

dependencies {
    api(project(":data"))

    implementation(project(":common"))
    implementation(libs.androidx.core.ktx)
}
