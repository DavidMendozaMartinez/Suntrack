package com.davidmendozamartinez.sunrating

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget as KotlinJvmTarget

object BuildDefaults {
    object AndroidSdk {
        const val MIN: Int = 24
        const val COMPILE: Int = 35
        const val TARGET: Int = 35
    }

    object JavaCompilation {
        val SourceCompatibility: JavaVersion = JavaVersion.VERSION_11
        val TargetCompatibility: JavaVersion = JavaVersion.VERSION_11
    }

    object KotlinCompiler {
        val JvmTarget: KotlinJvmTarget = KotlinJvmTarget.JVM_11
    }
}
