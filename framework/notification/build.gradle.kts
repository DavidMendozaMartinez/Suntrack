plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.framework.notification"
}

dependencies {
    api(project(":data"))
}
