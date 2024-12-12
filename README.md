Based on this : https://www.youtube.com/watch?v=j2V6SJ1ftZs

Running instrumented (end-to-end) tests using Gradle...

```bash
./gradlew connectedAndroidTest

# or for a specific module...
./gradlew moduleName:connectedAndroidTest
```

Running instrumented (end-to-end) tests on CI...

[Create a virtual Gradle-managed device](https://developer.android.com/studio/test/gradle-managed-devices)


```kotlin
// in your module-level build file...

android {
  testOptions {
    managedDevices {
      localDevices {
        create("pixel2api30") {
          // Use device profiles you typically see in Android Studio.
          device = "Pixel 2"
          // Use only API levels 27 and higher.
          apiLevel = 30
          // To include Google services, use "google".
          systemImageSource = "aosp"
        }
      }
    }
  }
}

// or for groups of devices...

testOptions {
  managedDevices {
    localDevices {
      create("pixel2api29") { /* ... */ }
      create("nexus9api30") { /* ... */ }
    }
    groups {
      create("phoneAndTablet") {
        targetDevices.add(devices["pixel2api29"])
        targetDevices.add(devices["nexus9api30"])
      }
    }
  }
}
```

Run your tests...

```bash
./gradlew deviceNameBuildVariantAndroidTest      # single device
./gradlew groupNameGroupBuildVariantAndroidTest  # device group

# e.g.
./gradlew pixel2api30DebugAndroidTest
./gradlew phoneAndTabletGroupDebugAndroidTest
```


```bash
# Output from a phone and tablet group...

./gradlew phoneAndTabletGroupDebugAndroidTest

> Task :app:pixel2api30DebugAndroidTest
Starting 3 tests on pixel2api30

pixel2api30 Tests 3/3 completed. (0 skipped) (0 failed)
Finished 3 tests on pixel2api30
03:31:05 I/XmlResultReporter: XML test result file generated at app/build/outputs/androidTest-results/managedDevice/debug/pixel2api30/TEST-pixel2api30-_app-.xml. Total tests 3, passed 3,

> Task :app:pixelTabletapi30DebugAndroidTest
Starting 3 tests on pixelTabletapi30

pixelTabletapi30 Tests 3/3 completed. (0 skipped) (0 failed)
Finished 3 tests on pixelTabletapi30
03:31:33 I/XmlResultReporter: XML test result file generated at app/build/outputs/androidTest-results/managedDevice/debug/pixelTabletapi30/TEST-pixelTabletapi30-_app-.xml. Total tests 3, passed 3,

> Task :app:mergeDebugAndroidTestTestResultProtos
Test execution completed. See the report at: file:///app/build/reports/androidTests/managedDevice/debug/allDevices/index.html

BUILD SUCCESSFUL in 2m 19s
65 actionable tasks: 5 executed, 60 up-to-date
```

`2m 19s` not fast, but a lot of that is device setup time - prob need to look into caching the image(s) if possible.


Note: [some functionality has been removed from the ATD images](https://developer.android.com/studio/test/gradle-managed-devices#atd-optimizations)

Interesting blog post: https://medium.com/wix-engineering/how-to-execute-android-ui-tests-on-ci-and-stay-alive-eb9089d88c1f
