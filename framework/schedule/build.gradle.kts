plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.framework.schedule"
}

dependencies {
    api(project(":data"))

    implementation(project(":common"))
}
