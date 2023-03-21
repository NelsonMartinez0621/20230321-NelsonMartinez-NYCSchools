# 20230321-NelsonMartinez-NYCSchools

A simple app that provides a list of schools in New York City and allows one to see their average SAT Scores. Built on Jetpack Compose with compose navigation, hilt and retrofit.

## Getting Started
You will need to an IDE capable of running a Kotlin project and either an emulator or real device with an appropriate SDK version. Simply import the code onto your device from the repository.

## Dependencies
Extra dependencies aside from those added with the Android Studio Compose Template
* Retrofit
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

* GSON - implementation 'com.google.code.gson:gson:2.10.1'

* Hilt -
    hilt_version ='2.40, 
    implementation "com.google.dagger:hilt-android:$hilt_version", 
    kapt "com.google.dagger:hilt-android-compiler:$hilt_version"

* LiveData with compose - 
    implementation 'androidx.compose.runtime:runtime-livedata:1.3.2'

* Navigation -
    nav_version - 2.5.3,
    implementation "androidx.navigation:navigation-compose:$nav_version"

* Unit testing coroutines -
    testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0', 
    testImplementation 'androidx.arch.core:core-testing:2.1.0'

* MockK -
    testImplementation "io.mockk:mockk:1.12.3"

## Built With
* Jetpack Compose - Used for UI development,
* Hilt - Used for Dependency Injection,
* Retrofit - Used to retreave the data from the APIs,
* MockK - Used for JUnit testing
* Android Studio
