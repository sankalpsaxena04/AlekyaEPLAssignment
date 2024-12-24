plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.plugin.serialization")

}

android {
    namespace = "com.sandev.alekyaeplassignment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sandev.alekyaeplassignment"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding= true
        dataBinding= true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Gson
    implementation ("com.google.code.gson:gson:2.11.0")

    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-android-compiler:2.49")
    implementation ("androidx.hilt:hilt-common:1.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
}
kapt {
    correctErrorTypes = true
}
