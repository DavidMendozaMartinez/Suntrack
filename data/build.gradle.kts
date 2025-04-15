plugins {
    id("sunrating.jvm.library")
}

dependencies {
    api(project(":domain"))

    implementation(libs.javax.inject)
}
