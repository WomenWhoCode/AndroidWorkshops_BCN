# Day 1 - Kotlin introduction and Android Components

In this day, you'll learn how to build and run your first Android app in Kotlin. Kotlin is a statically typed programming language that runs on the JVM and is completely interoperable with Java. Kotlin is an officially supported language for developing Android apps, along with Java.

If you like guided tutorials, you might want to check out this codelab [build-your-first-android-app-kotlin](https://codelabs.developers.google.com/codelabs/build-your-first-android-app-kotlin/index.html?index=..%2F..index#0) we have based this workshop on the codelab 

## What you'll learn

- How to use Android Studio to build your app.
- How to run your app on a device or in the emulator.
- How to add interactive buttons.
- How to display a second screen.

## What you must know already

This codelab is written for programmers and assumes you know either Java or Kotlin. If you are an experienced programmer and you are adept at reading code, you will likely be able to follow this codelab even if you don't have much experience with Kotlin.

If you do not know any Kotlin, check this [Kotlin Basic Syntax](https://kotlinlang.org/docs/reference/basic-syntax.html) link it will teach you what you need to know to move forward with the workshop. 

To work through this codelab, you will need a computer that can run Android Studio (or already has Android Studio installed). Install Android Studio following [this instructions](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Installation.md)


## Homework
You will use [this starting project](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/tree/master/Day%201%20-%20Kotlin%20Basics/DoggoApp) for the homework AFTER the first workshop. Import the project DoggoApp in Android Studio.

### Exercise 1

You will be working on the first screen making changes to `MainActivity.kt` and `activity_main.xml`

* ✓Tip: Tap shift button when in Android Studio to search for these files, try it now...
* ✓Tip 2: You can use some of the code you wrote in the codelab and reuse it.

Open `activity_main.xml`
As you can see, the root layout is `LinearLayout` which is less powerful than the `ConstraintLayout` you used during the codelab but also easier to use especially for simple layouts. You will lrarn more about layout in the next workshop.
* Add 2 buttons (`Button`). You can drag them using the layout editor (recommended) or write the xml code yourselves.
The first button text should be "Huge dog" and the second one "Tiny dog".
* Set the first button text size to 20sp.
* Set the second button size to 12sp.
* Add a top margin to the bottom button to separate it from the top one. Try 16dp. You can play with this margin modifying it until you find one that you think looks good.
* ✓Tip: sp (scalable pixels) and dp (density-independent pixels) are units used by Android to make sure your views look nice on all screen sizes, [check here to find out more](https://developer.android.com/training/multiscreen/screendensities).


### Exercise 2

Add click listeners to both buttons. When clicking "huge dog" the user should see `HugeDogActivity` and "tiny dog", `TinyDogActivity`. These classes have already been added to the project. Use what have you learned during the codelab.
To help you with opening an activity, the code for viewing `HugeDogActivity` has already been added to `MainActivity`.


### Exercise 3

Add one image view to `activity_huge_dog` and one to `activity_tiny_dog`. Inside `res/drawable` you will find 2 images to use as source for each activity. To help you check the [official documentation](https://developer.android.com/reference/kotlin/android/widget/ImageView)

### Final result

<img src="https://user-images.githubusercontent.com/923280/56064626-da33e600-5d72-11e9-9c18-86f96cdae23e.gif" width="100" />
