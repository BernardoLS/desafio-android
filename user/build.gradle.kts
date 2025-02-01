plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.picpay.desafio.android.user"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //modules
    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":database"))

    //libs
    implementation(libs.coroutine.core)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.glide)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.androidx.room.common)
    implementation(libs.room.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.constraint.layout)
    implementation(libs.swipe.refresh)
    implementation(libs.androidx.espresso.core)
    ksp(libs.room.compiler)


    testImplementation(libs.junit)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter)
}