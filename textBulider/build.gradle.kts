plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create("release", MavenPublication::class.java) {
                from(components["release"])
                groupId = "com.github.LimGyeongMo"
                artifactId = ""
                version = "0.0.1"
            }

            // Creates a Maven publication called "debug".
            create("debug", MavenPublication::class.java) {
                from(components["debug"])
                groupId = "com.github.LimGyeongMo"
                artifactId = "final-debug"
                version = "0.0.1"
            }
        }
    }
}

android {
    namespace = "com.project.textbulider"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    resourcePrefix("")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}