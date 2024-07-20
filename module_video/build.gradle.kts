plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.android.library")
    id("kotlin-kapt")
//    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.module_video"
    compileSdk = 34
    val isBuildModule = false
    defaultConfig {
        minSdk = 24
        targetSdk = 34
//        applicationId = "com.example.module_video"
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets {
        getByName("main") {
            if (isBuildModule) {
                manifest.srcFile("src/debug/AndroidManifest.xml")
            } else {
                manifest.srcFile("src/main/AndroidManifest.xml")
            }
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
    viewBinding {
        enable = true
    }
}
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}
dependencies {
    implementation(project(":lib_net"))
    //第三方播放库
    implementation("xyz.doikki.android.dkplayer:dkplayer-java:3.3.6")
    implementation("xyz.doikki.android.dkplayer:player-exo:3.3.6")
    implementation("xyz.doikki.android.dkplayer:player-ijk:3.3.6")
    implementation("xyz.doikki.android.dkplayer:dkplayer-ui:3.3.6")
    //网络请求库
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("io.reactivex.rxjava3:rxjava:3.0.13")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    //jetpack
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    //Glide图片加载
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    kapt("com.alibaba:arouter-compiler:1.5.2")
    implementation("com.alibaba:arouter-api:1.5.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}