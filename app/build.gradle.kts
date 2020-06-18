plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(Versions.Android.compileSdkVersion)
    buildToolsVersion(Versions.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdkVersion)
        targetSdkVersion(Versions.Android.targetSdkVersion)

        applicationId = Flavors.App.applicationId
        versionCode = Versions.App.buildNumber
        versionName = Versions.App.appVersion

        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        create("release") {
//            keyAlias = ""
//            keyPassword = ""
//            storePassword = ""
//            storeFile = file("")
        }
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false

//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "$project.rootDir/proguard-rules.pro"
//            )

//            firebaseAppDistribution {
//                serviceCredentialsFile = file("../app_distribution_key.json").absolutePath
//                groups = "admin"
//            }
        }
    }

    lintOptions.isAbortOnError = false

    compileOptions {
        setSourceCompatibility(1.8)
        setTargetCompatibility(1.8)
    }
}

dependencies {
    implementation(Depends.kotlinStdLib)

    implementation(project(Depends.Modules.coreDesign))
    implementation(project(Depends.Modules.coreFeature))
    implementation(project(Depends.Modules.network))
    implementation(project(Depends.Modules.dataCurrencyRates))
    implementation(project(Depends.Modules.featureCurrencyConverter))

    implementation(Depends.androidAppCompat)
    implementation(Depends.androidCoreKtx)
    implementation(Depends.androidConstraintLayout)
    implementation(Depends.androidNavigationFragmentKtx)
    implementation(Depends.androidNavigationUiKtx)

    implementation(Depends.rxJava)

    implementation(Depends.dagger)
    kapt(Depends.daggerCompiler)
    implementation(Depends.daggerAndroid)
    implementation(Depends.daggerAndroidSupport)
    kapt(Depends.daggerProcessor)

    implementation(Depends.firebaseAnalytics)
    implementation(Depends.firebaseCrashlytics)

    api(Depends.roomRuntime)
    kapt(Depends.roomCompiler)
    implementation(Depends.roomKtx)
    implementation(Depends.roomRxJava2)
    testImplementation(Depends.roomTesting)
}

apply(plugin = "com.google.gms.google-services")
