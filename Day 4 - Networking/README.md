# Day 4 - Networking

This course is designed for students who have completed [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%201%20-%20Kotlin%20Basics/),  [Day 2](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%202%20-%20Android%20Basics/) and [Day 3](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%203%20-%20Multi%20Screen%20Apps/). 

By the end of the course, you’ll build an app that give you real state info of Mars! In this session we are going to follow this codelab [Android Kotlin Fundamentals 08.1: Getting data from the internet](https://codelabs.developers.google.com/codelabs/kotlin-android-training-internet-data/index.html)

If you like guided tutorials, and you want to practice more you might want to check out this course [Android Basics: Networking](https://eu.udacity.com/course/android-basics-networking--ud843).  

## What you'll learn

- Getting Data from internet
- Basic modern Android arquitecture
- Loading and displaying images from internet
- Using libraries like Retrofit, Android Architecture Components and Coroutines.

## What you must know already

This codelab is written for programmers and assumes you know either Java or Kotlin. If you are an experienced programmer and you are adept at reading code, you will likely be able to follow this codelab even if you don't have much experience with Kotlin.

If you do not know any Kotlin, check this [Kotlin Basic Syntax](https://kotlinlang.org/docs/reference/basic-syntax.html) link it will teach you what you need to know to move forward with the workshop. 

If you missed the past day check out [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%201%20-%20Kotlin%20Basics/),  [Day 2](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%202%20-%20Android%20Basics/) and [Day 3](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%203%20-%20Multi%20Screen%20Apps/).

To work through this codelab, you will need a computer that can run Android Studio (or already has Android Studio installed). Install Android Studio following [this instructions](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Installation.md)

## Homework
After completing the codelab, we will continue with the existing DoggoApp. If you do not have your own project, just use the one in [DoggoApp](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/tree/homework-day4/Day%201%20-%20Kotlin%20Basics/DoggoApp) which has the solution for the day 3 homework. We will base the work on that project. During the last codelab you displayed a list of dogs but we used manually generated data for that, remember the method:

```
    private fun doggoList() = listOf(
        Doggo("Toby", "3 months", "Big", R.drawable.doggo_1),
        Doggo("Snowy", "2 months", "Big", R.drawable.doggo_2),
        Doggo("Luca", "8 months", "Medium", R.drawable.doggo_3),
        Doggo("Chispi", "2 years", "Small", R.drawable.doggo_4),
        Doggo("Willy", "6 months", "Big", R.drawable.huge_dog_malamute),
        Doggo("Coco", "3 years", "Small", R.drawable.tiny_dog_norfolk),
        Doggo("Balto", "3 months", "Big", R.drawable.doggo_1),
        Doggo("Boby", "2 months", "Big", R.drawable.doggo_2)
    )

```
In real life, we usually want to display data we get from the internet. Here is the url with the json data containing a list of dogs: https://github.com/valllllll2000/DogApi/blob/master/list.json

Take a moment to look at that file. Here is an example of a single dog information as a JSON object:
```
{
    "name": "Maltese",
    "age": "15 - 18 years",
    "size": "20 - 25 cm",
    "id": "B1SV7gqN7",
    "url": "https://cdn2.thedogapi.com/images/B1SV7gqN7_1280.jpg",
    "width": 680,
    "height": 453
  }
```
And here is our Doggo object
```
data class Doggo(val name: String, val age: String, val size: String, val image: Int)
```
As you can see some fields in the JSON object are interesting for us:
* name
* age
* size
They are called the same in the Doggo object. What about url? The url is the address where the image is located. In the Doggo object there is no url field. 

1) Doggo object

Open Doggo.kt file and replace `val image: Int` by `val url: String`.

This is the new Doggo data class:
```
data class Doggo(val name: String, val age: String, val size: String, val url: String)
```

2) Permissions and dependencies

In `AbdroidManifest`, just below the camera permission you need to add:
```
<uses-permission android:name="android.permission.INTERNET"/>
```
By default, the apps do not have access to the internet. Learn more about Android permissions [here](https://developer.android.com/guide/topics/permissions/overview).

Open `app/build.gradle` and add all these dependencies or libraries inside `dependencies {}`, yes we will need them all :smile:
```
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-moshi:2.5.0'
    implementation 'com.squareup.moshi:moshi:1.8.0'
    implementation 'com.squareup.moshi:moshi-kotlin:1.8.0'

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.0'

    implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'

    implementation 'com.github.bumptech.glide:glide:4.8.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
```

  3) Networking
  In android Studio right click on the pa ckage name `com.womenwhocode.workshop.doggoapp` and select New -> package. Name it `ǹetworking`. Inside the networking package create a new Kotlin file called DogApiService. Here you can use the codelab's code to fill the missing ñpgic to get your dog list.
  
  * base url is "https://raw.githubusercontent.com/valllllll2000/DogApi/master/"
  * copy the restrofit and moshi properties, they are identical to the codelabs one.
  * in the same file add an interface called `DogApiService` with a method called `getDoggos()` which should return `Deferred<List<Doggo>>`. The GET annotation will be like this: `@GET("list.json")`
  * Add a DogApi object which will return a DogApiService
  
