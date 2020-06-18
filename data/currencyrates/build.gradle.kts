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
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(Depends.kotlinStdLib)
    implementation(Depends.kotlinReflect)

    implementation(project(Depends.Modules.network))

    implementation(Depends.androidCoreKtx)

    implementation(Depends.retrofit)
    implementation(Depends.retrofitRxJava2Adapter)
    implementation(Depends.retrofitGsonConverter)
    implementation(Depends.okhttpLoggingInterceptor)

    implementation(Depends.rxJava)
    implementation(Depends.rxAndroid)

    implementation(Depends.dagger)
    kapt(Depends.daggerCompiler)
    implementation(Depends.daggerAndroid)
    implementation(Depends.daggerAndroidSupport)
    kapt(Depends.daggerProcessor)

    api(Depends.roomRuntime)
    kapt(Depends.roomCompiler)
    implementation(Depends.roomKtx)
    implementation(Depends.roomRxJava2)
    testImplementation(Depends.roomTesting)
}
