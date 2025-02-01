plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.picpay.desafio.android.database"
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
    implementation(project(":network"))

    implementation(libs.annotations)
    constraints {
        implementation("com.intellij:annotations:12.0") {
            because("Evitar conflito com org.jetbrains:annotations")
        }
    }

    //libs
    implementation(libs.coroutine.core)
    implementation(libs.room.runtime)
    implementation(libs.room.compiler)
    implementation(libs.room.ktx)

    androidTestImplementation(libs.room.test)
    androidTestImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.android.test.runner)
    androidTestImplementation(libs.androidx.arch.core.testing)

}