plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.picpay.desafio.android.core"
    compileSdk = 34

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
}

dependencies {
    //libs
    implementation(libs.androidx.appcompat)
    implementation(libs.constraint.layout)
    implementation(libs.material)
    implementation(libs.core.ktx)
    implementation(libs.koin.core)
    implementation(libs.coroutine.core)
    implementation(libs.glide)
    implementation(libs.circle.image)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.activity)

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