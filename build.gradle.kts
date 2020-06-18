buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(Depends.BuildPlugins.androidPlugin)
        classpath(Depends.BuildPlugins.kotlinPlugin)
        classpath(Depends.BuildPlugins.googleServicesPlugin)
        classpath(Depends.BuildPlugins.firebaseAppDistributionPlugin)
        classpath(Depends.BuildPlugins.firebaseCrashlyticsPlugin)
        classpath(Depends.BuildPlugins.androidNavigationSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.google.com")
        maven(url = "https://jitpack.io")
        maven(url = "https://mvnrepository.com")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
