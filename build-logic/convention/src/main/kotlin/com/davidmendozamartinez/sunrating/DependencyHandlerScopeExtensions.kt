package com.davidmendozamartinez.sunrating

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addCommonKotlinDependencies() {
    add(configurationName = "implementation", dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    add(configurationName = "implementation", dependencyNotation = "org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")
    add(configurationName = "implementation", dependencyNotation = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.8")
}
