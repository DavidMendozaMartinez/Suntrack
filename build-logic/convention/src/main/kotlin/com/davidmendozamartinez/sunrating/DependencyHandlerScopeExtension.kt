package com.davidmendozamartinez.sunrating

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addKotlinCommonDependencies() {
    add(configurationName = "implementation", dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    add(configurationName = "implementation", dependencyNotation = "org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
}
