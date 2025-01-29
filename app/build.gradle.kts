plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.picpay.desafio.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        debug {}

        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.constraint.layout)
    implementation(libs.picasso)
    implementation(libs.circle.image)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)
    implementation(libs.gson)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.mockwebserver)
    implementation(libs.square.okhttp.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.activity)

    testImplementation(libs.junit.test)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.junit4)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.core.ktx.android.test)
    androidTestImplementation(platform(libs.android.test.runner))
}
