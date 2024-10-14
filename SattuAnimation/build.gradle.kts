plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Ensure this matches the Compose Compiler version
    }
    namespace = "com.megamart.sattuanimation"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(platform(libs.androidx.compose.bom)) // Ensure you are using the BOM correctly
    implementation(libs.androidx.ui)
    implementation("androidx.activity:activity-compose:1.7.2")

    // Jetpack Compose dependencies
    implementation("androidx.compose.foundation:foundation:1.5.0")
    implementation("androidx.compose.material3:material3:1.2.0") // Latest Material3 version

    // Note: Jetpack Compose compiler is usually not required as a direct dependency unless you are using specific features.
    implementation("androidx.compose.compiler:compiler:1.5.3") // Ensure this is compatible with your Compose version


}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.SatyamkrJha85"
                artifactId = "SattuAnim"
                version = "1.1"
            }
        }
    }
}