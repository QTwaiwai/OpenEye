plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.openeye"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.openeye"
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
}
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}


dependencies {
    implementation("xyz.doikki.android.dkplayer:dkplayer-java:3.3.6")
    implementation("xyz.doikki.android.dkplayer:player-exo:3.3.6")
    implementation("xyz.doikki.android.dkplayer:player-ijk:3.3.6")
    implementation("xyz.doikki.android.dkplayer:dkplayer-ui:3.3.6")
    implementation(libs.transportation.consumer)
    implementation ("com.alibaba:arouter-api:1.5.2")
    kapt  ("com.alibaba:arouter-compiler:1.5.2")
    implementation(project(":module_community"))
    implementation(project(":module_home"))
    /*debugImplementation("com.squareup.leakcanary:leakcanary-android:3.0-alpha-1")*/
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}