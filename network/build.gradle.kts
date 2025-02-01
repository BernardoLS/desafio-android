plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.picpay.desafio.android.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    configurations {
        create("cleanedAnnotations")
        getByName("implementation").exclude(group = "org.jetbrains", module = "annotations")
    }
}

dependencies {
    //modules
    implementation(project(":core"))


    implementation(libs.annotations)
    constraints {
        implementation("com.intellij:annotations:12.0") {
            because("Evitar conflito com org.jetbrains:annotations")
        }
    }
    //libs
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.mockwebserver)
    implementation(libs.square.okhttp.logging.interceptor)
    implementation(libs.coroutine.core)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.slf4j)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.koin.junit4)
    testImplementation(libs.mockk)
}