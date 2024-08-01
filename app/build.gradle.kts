plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")

}

android {
    namespace = "com.example.simplecleanarchitecturepokemonapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.simplecleanarchitecturepokemonapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.mockito.core)
    testImplementation(libs.junit.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.jetbrains.kotlinx.coroutines.android)
    ///retrofit
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    ///hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.47")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.47")
    //splashscreen
    implementation(libs.androidx.core.splashscreen)
    /////COIL
    implementation(libs.coil)

    // Room runtime
    implementation ("androidx.room:room-runtime:2.6.1")

    // Room compiler for annotation processing
    kapt ("androidx.room:room-compiler:2.6.1")

    // Room KTX for Kotlin coroutines support
    implementation ("androidx.room:room-ktx:2.6.1")
    // Room Testing
    testImplementation ("androidx.room:room-testing:2.6.1")

}