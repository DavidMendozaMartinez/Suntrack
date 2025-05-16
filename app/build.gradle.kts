plugins {
    id("sunrating.android.application")
    id("sunrating.hilt")
    id("sunrating.compose")
}

android {
    namespace = "com.davidmendozamartinez.sunrating"

    defaultConfig {
        applicationId = "com.davidmendozamartinez.sunrating"

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:events"))
    implementation(project(":feature:places"))
    implementation(project(":feature:settings"))
    implementation(project(":framework:database"))
    implementation(project(":framework:location"))
    implementation(project(":framework:network"))
    implementation(project(":framework:notification"))
    implementation(project(":framework:preferences"))
    implementation(project(":framework:schedule"))
}
