# Day 5 - Data Storage

This course is designed for students who have completed [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day1%20-%20Kotlin%20Basics/),  [Day 2](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%202%20-%20Android%20Basics/),  [Day 3](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%203%20-%20Multi%20Screen%20Apps/) and [Day 4](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%204%20-%20Networking/).

In this course, you will learn about the importance of data persistence when building an Android app. We'll introduce you to the fundamentals of SQL, the programming language needed to interact with an SQLite relational database. SQLite is a commonly used method to store large sets of data locally on an Android device.
If you haven't used SQLite or if you need to refresh your memory, please read this [introductory document first](https://developer.android.com/courses/extras/sql-primer).
During the workshop, we will follow this [codelab](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#0)
 

## What you'll learn

- SQL fundamentals
- Using [Room](https://developer.android.com/topic/libraries/architecture/room) to store and retrieve data in the device's SQLite database.

## What you must know already

This codelab is written for programmers and assumes you know either Java or Kotlin. If you are an experienced programmer and you are adept at reading code, you will likely be able to follow this codelab even if you don't have much experience with Kotlin.

If you do not know any Kotlin, check this [Kotlin Basic Syntax](https://kotlinlang.org/docs/reference/basic-syntax.html) link it will teach you what you need to know to move forward with the workshop. 

If you missed the past day check out [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day1%20-%20Kotlin%20Basics/), [Day 2](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%202%20-%20Android%20Basics/) and [Day 3](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%203%20-%20Multi%20Screen%20Apps/) and [Day 4](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%204%20-%20Networking/).

To work through this codelab, you will need a computer that can run Android Studio (or already has Android Studio installed). Install Android Studio following [this instructions](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Installation.md)

## Homework
1) Activities cleanup
We have been adding several activities to our app but the current structure of the app is not ideal. Lets modify a few things.
* We want `DoggosActivity`, our list of dogs, to start when we launch the app. Take a look at the `AndroidManifest`. Which activity starts first right now? In case you changed the name it's the activity which contains:
```
   <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
   </intent-filter>
```
The code above tells Android which activity should be started first when the app is launched. In order for `DoggosActivity` to be started first, we need to add the intent filter to it and remove it from `MainActivity`.
Once you have done that, try running the app. You should see the list of dogs. 
* The idea is to add dogs to that list and we already have a form for that but now we need a way to access it. Lets add a `FloatingActionButton` to our `DoggosActivity`.
Go to `app/build.gradle` and add the following dependency at the bottom of the others:
`implementation 'com.google.android.material:material:1.0.0'`
After adding that you will see a "sync now" link at the top of the screen. Press that so the IDE downloads the new dependency.

Open the layout xml file of `DoggosActivity` and drag a FloatingActionButton into area below recycler_view. 

<img src="https://user-images.githubusercontent.com/923280/61551389-5a927300-aa55-11e9-955c-17dea00bd1b9.png" width="300">

You will see a dialog as on the screenshot below, chose the "ic_menu_add.png" or another resource of your choice.

<img src="https://user-images.githubusercontent.com/923280/61550431-b4de0480-aa52-11e9-9006-0c2c9182a942.png" width="400">

Try placing the FAB at the bottom right of the screen.
Here is the full code for the action button:
```
  <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:clickable="true"
            app:srcCompat="@android:drawable/ic_menu_add"
            android:id="@+id/add_doggo"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            android:layout_marginEnd="16dp"
            android:layout_marginBottom="16dp"/>
```
