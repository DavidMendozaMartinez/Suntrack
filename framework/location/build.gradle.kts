plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.framework.location"
}

dependencies {
    api(project(":data"))

    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.play.services.location)
}
