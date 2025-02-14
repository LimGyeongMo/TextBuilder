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
                version = "0.0.3"
            }

            // Creates a Maven publication called "debug".
            create("debug", MavenPublication::class.java) {
                from(components["debug"])
                groupId = "com.github.LimGyeongMo"
                artifactId = "final-debug"
                version = "0.0.3"
            }
        }
    }
}

android {
    namespace = "com.project.textbulider"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

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
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}