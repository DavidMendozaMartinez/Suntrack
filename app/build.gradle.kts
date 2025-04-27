plugins {
    id("sunrating.android.application")
    id("sunrating.hilt")
    alias(libs.plugins.kotlin.compose)
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

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":framework:database"))
    implementation(project(":framework:location"))
    implementation(project(":framework:network"))
    implementation(project(":framework:notification"))
    implementation(project(":framework:preferences"))
    implementation(project(":framework:schedule"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.hilt.navigation.compose)

    testImplementation(libs.junit)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
