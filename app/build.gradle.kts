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
    //modules
    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":database"))
    implementation(project(":user"))

    //libs
    implementation(libs.androidx.appcompat)
    implementation(libs.constraint.layout)
    implementation(libs.material)
    implementation(libs.koin.android)
    implementation(libs.picasso)
    implementation(libs.circle.image)
    implementation(libs.coroutine.android)

    //unit-test
    testImplementation(libs.junit.test)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.junit4)

    //ui-test
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.core.ktx.android.test)
    androidTestImplementation(platform(libs.android.test.runner))
}
