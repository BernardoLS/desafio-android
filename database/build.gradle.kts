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
    configurations {
        all {
            exclude(group = "com.intellij", module = "annotations")
        }
    }
    implementation(project(":core"))
    implementation(project(":network"))

    //libs
    implementation(libs.coroutine.core)
    implementation(libs.room.runtime)
    implementation(libs.room.compiler)
    implementation(libs.room.ktx)

    //ui-test
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.core.ktx.android.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.room.test)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.android.test.runner)
    androidTestImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.coroutine.test)
    androidTestImplementation(platform(libs.android.test.runner))

}