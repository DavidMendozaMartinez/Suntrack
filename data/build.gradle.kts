plugins {
    id("sunrating.jvm.library")
}

dependencies {
    api(project(":domain"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.javax.inject)
}
