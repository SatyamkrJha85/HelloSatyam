# HelloSatyam - Animated Profile Image Library for Jetpack Compose

[![License](https://img.shields.io/github/license/SatyamkrJha85/SattuAnim)](LICENSE)
[![Version](https://img.shields.io/badge/version-1.1-blue.svg)](https://jitpack.io/#SatyamkrJha85/SattuAnim)

**SattuAnim** is a Jetpack Compose library that provides customizable and animated profile images with seamless transitions. This library enables users to add, modify, and animate images in a fun and engaging way, making it perfect for apps requiring user profile customizations.

## 📱 Features

- **Profile Image Animations**: Animate profile images with smooth scale, rotation, and fade effects.
- **Color Customization**: Change the border color dynamically using an interactive color picker.
- **Customizable Animation Properties**: Control the animation speed, border width, rotation angle, and opacity via sliders.
- **Jetpack Compose Ready**: Modern UI development using Jetpack Compose's declarative approach.

## 🚀 Getting Started

### Prerequisites

- **Android Studio** 4.2 or above.
- **Kotlin** 1.5.21 or above.
- Jetpack Compose set up in your project.

### Installation

Add the JitPack repository to your project's `build.gradle`:

```gradle
allprojects {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency in your module's build.gradle file:
```
dependencies {
    implementation 'com.github.SatyamkrJha85:SattuAnim:1.1'
}
```
###📖 Usage
To start using SattuAnim, simply integrate the HelloSatyam composable into your UI.

```import androidx.compose.runtime.Composable
import com.megamart.sattuanimation.HelloSatyam

@Composable
fun ProfileScreen() {
    // Load a drawable resource for the profile image
    HelloSatyam(img = R.drawable.sample_profile_image)
}
```

# Screenshot

<img src="https://github.com/user-attachments/assets/416af59a-8c62-43fc-864c-c9a4151dd40c" alt="Profile Image Animation" width="400"/>

<img src="https://github.com/user-attachments/assets/10b155cb-8b71-479f-83e9-14f23156bdec" alt="Color Picker & Sliders" width="400"/>


