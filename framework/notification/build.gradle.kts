plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.sunrating.framework.notification"
}

dependencies {
    api(project(":data"))

    implementation(project(":common"))
}
