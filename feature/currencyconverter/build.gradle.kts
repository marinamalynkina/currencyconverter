plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)
        vectorDrawables.useSupportLibrary = true
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(Depends.kotlinStdLib)

    api(project(Depends.Modules.coreDesign))
    api(project(Depends.Modules.coreFeature))
    api(project(Depends.Modules.dataCurrencyRates))

    implementation(Depends.androidAppCompat)
    implementation(Depends.androidCoreKtx)
    implementation(Depends.androidMaterial)
    implementation(Depends.androidConstraintLayout)
    implementation(Depends.androidSwipeLayout)

    implementation(Depends.androidLifecycleExtensions)
    kapt(Depends.androidLifecycleCompiler)
    implementation(Depends.androidLifecycleLiveDataKtx)

    implementation(Depends.rxJava)
    implementation(Depends.rxAndroid)

    implementation(Depends.dagger)
    kapt(Depends.daggerCompiler)
    implementation(Depends.daggerAndroid)
    implementation(Depends.daggerAndroidSupport)
    kapt(Depends.daggerProcessor)

    implementation(Depends.facebookShimer)

    testImplementation(Depends.jUnit)
    testImplementation(Depends.jUnitCore)
    testImplementation(Depends.mochito)

}
