plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.picpay.desafio.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            buildConfigField("String", "BASE_URL", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
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
        buildConfig = true
    }

    configurations {
        create("cleanedAnnotations")
        getByName("implementation").exclude(group = "org.jetbrains", module = "annotations")
    }

}

dependencies {
    //modules
    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":database"))
    implementation(project(":user"))

    implementation(libs.annotations)
    constraints {
        implementation("com.intellij:annotations:12.0") {
            because("Evitar conflito com org.jetbrains:annotations")
        }
    }
    //libs
    implementation(libs.androidx.appcompat)
    implementation(libs.constraint.layout)
    implementation(libs.material)
    implementation(libs.koin.android)
    implementation(libs.coroutine.android)
}
