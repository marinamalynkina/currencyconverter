object Depends {

    object Modules {
        const val app = ":app"
        const val network = ":network"
        const val coreDesign = ":core_design"
        const val coreFeature = ":core_feature"

        const val dataCurrencyRates = ":data-currencyrates"
        const val featureCurrencyConverter = ":feature-currencyconverter"
    }

    object BuildPlugins {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidGradlePluginVersion}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val googleServicesPlugin = "com.google.gms:google-services:${Versions.googleServicesVersion}"
        const val firebaseAppDistributionPlugin = "com.google.firebase:firebase-appdistribution-gradle:${Versions.firebaseAppDistributionVersion}"
        const val firebaseCrashlyticsPlugin = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsPluginVersion}"
        const val androidNavigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigationVersion}"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"

    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitRunner = "androidx.test:runner:${Versions.jUnitRunnerVersion}"
    const val jUnitCore = "androidx.test:core:${Versions.jUnitCoreVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val mochito = "org.mockito:mockito-core:${Versions.mochitoVersion}"

    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompatVersion}"
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.androidCoreKtxVersion}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterialVersion}"
    const val androidLegacySupport = "androidx.legacy:legacy-support-v4:${Versions.androidLegacySupportVersion}"
    const val androidRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidRecyclerViewVersion}"
    const val androidLifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidLifecycleVersion}"
    const val androidLifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.androidLifecycleVersion}"
    const val androidLifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycleVersion}"
    const val androidLifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidLifecycleVersion}"
    const val androidFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidFragmentKtxVersion}"
    const val androidConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidConstraintLayoutVersion}"
    const val androidNavigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigationVersion}"
    const val androidNavigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigationVersion}"
    const val androidSwipeLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidSwipeLayoutVersion}"

    const val playServicesAuth = "com.google.android.gms:play-services-auth:${Versions.playServicesAuthVersion}"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalyticsVersion}"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlyticsVersion}"

    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitRxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomRxJava2 = "androidx.room:room-rxjava2:${Versions.roomVersion}"
    const val roomTesting = "androidx.room:room-testing:${Versions.roomVersion}"

    const val facebookShimer = "com.facebook.shimmer:shimmer:${Versions.facebookShimerVersion}"

}
