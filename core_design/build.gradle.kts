plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion(Versions.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "$project.rootDir/proguard-rules.pro"
//            )
        }
    }
}

androidExtensions {
    features = setOf("parcelize")
}

dependencies {
    implementation(Depends.kotlinStdLib)

    implementation(Depends.androidAppCompat)
    implementation(Depends.androidMaterial)
    implementation(Depends.androidCoreKtx)
    implementation(Depends.androidConstraintLayout)
}
