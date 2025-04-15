plugins {
    id("sunrating.android.library")
    id("sunrating.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.davidmendozamartinez.framework.database"
}

dependencies {
    api(project(":data"))

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)

    ksp(libs.room.compiler)
}
