plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.picpay.desafio.android.core"
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

    buildFeatures {
        androidResources = true
    }
    configurations {
        create("cleanedAnnotations")
        getByName("implementation").exclude(group = "org.jetbrains", module = "annotations")
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
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.activity)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.timber)

    implementation(libs.annotations)
    constraints {
        implementation("com.intellij:annotations:12.0") {
            because("Evitar conflito com org.jetbrains:annotations")
        }
    }
    //unit-test
    testImplementation(libs.coroutine.test)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.junit4)
    testImplementation(libs.mockk)
    testImplementation(libs.junit.jupiter)
}