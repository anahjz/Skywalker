plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

val compose_version = "1.5.2"

android {
    compileSdk = 34
    namespace = "com.skywalker.app"

    defaultConfig {
        applicationId = "com.skywalker.app"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.tooling)
    implementation(libs.compose.material)
    implementation(libs.androidx.lifecycle)
    implementation(libs.compose.activity)
    implementation(libs.compose.constraint)
    implementation(libs.compose.navigation)
    implementation(libs.compose.paging)
    implementation(libs.androidx.livedataruntime)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.livedata)
    implementation(libs.pager)
    implementation(libs.pager.indicator)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.compose)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.logging)
    implementation(libs.retrofit.gson)
    implementation(libs.coil)
    testImplementation(libs.junit)



    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

}

kapt {
    correctErrorTypes = true
}

kapt {
    javacOptions {
        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
    }
}