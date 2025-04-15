import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.davidmendozamartinez.sunrating.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}


gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "sunrating.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "sunrating.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("jvmLibrary") {
            id = "sunrating.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("hilt") {
            id = "sunrating.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}
