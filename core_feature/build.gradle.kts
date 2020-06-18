plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion(Versions.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        vectorDrawables.useSupportLibrary = true
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/proguard-rules.pro"
            )
        }
    }
}

androidExtensions {
    features = setOf("parcelize")
}

dependencies {

    implementation(project(Depends.Modules.coreDesign))
    api(project(Depends.Modules.network))

    implementation(Depends.kotlinStdLib)

    implementation(Depends.androidAppCompat)
    implementation(Depends.androidMaterial)
    implementation(Depends.androidCoreKtx)
    implementation(Depends.androidConstraintLayout)

    implementation(Depends.rxJava)
    implementation(Depends.rxAndroid)
}
