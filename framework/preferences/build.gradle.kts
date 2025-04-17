plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
}

android {
    namespace = "com.davidmendozamartinez.sunrating.framework.preferences"
}

dependencies {
    api(project(":data"))
}
