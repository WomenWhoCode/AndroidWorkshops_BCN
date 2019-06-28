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

In `AndroidManifest`, just below the camera permission you need to add:
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
  
  In android Studio right click on the pa ckage name `com.womenwhocode.workshop.doggoapp` and select New -> package. Name it `networking`. Inside the networking package create a new Kotlin file called DogApiService. Here you can use the codelab's code to fill the missing logic to get your dog list.
  
  * base url is "https://raw.githubusercontent.com/valllllll2000/DogApi/master/"
  * copy the restrofit and moshi properties, they are identical to the codelabs one.
  * in the same file add an interface called `DogApiService` with a method called `getDoggos()` which should return `Deferred<List<Doggo>>`. The GET annotation will be like this: `@GET("list.json")`
  * Add a DogApi object which will return a `DogApiService`.
  
4) List and ViewModel

 In android Studio right click on the pa ckage name `com.womenwhocode.workshop.doggoapp` and select New -> package. Name it `list`. Move the DoggosAdapter and DoggosActivity to the package `list`. Create the `DoggoViewModel` kotlin class. Here is how it should look:
```
class DoggoViewModel: ViewModel() {
    private val doggos: MutableLiveData<List<Doggo>> = MutableLiveData()
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
}
```
DoggoViewModel extends [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) class. 

Inside DoggoViewModel you need to define 2 methods

```
fun getDoggos(): MutableLiveData<List<Doggo>> {     
}
```
The logic of this method, check if doggos has a null or empty value and if that's the case call the `loadDoggos()` and then always return `doggos`.

You also need to define the `loadDoggos()` private method. It should use the coroutines and the `DogApi` to load the dogs and setup the doggos value with the result. Use the codelab's code as example.

Same as in the codelab add a `viewModel` to `DoggosActivity`. 
Delete the `doggoList()` method we will no use it anymore. 
Replace all the `initRecylerView()` code by:
```
val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
val doggosAdapter = DoggosAdapter()
recyclerView.adapter = doggosAdapter
viewModel.getDoggos().observe(this, Observer<List<Doggo>> { dogs -> dogs?.let { doggosAdapter.displayDoggos(it) } })
```
The first 2 lines stay the same, we access the recyclerView and add a StaggeredGridLayoutManager to it.
We then initialize the DoggosAdapter. You will get compilation errors at this point. 
Open `DoggosAdapter` and change its signature to 
```
class DoggosAdapter(private val doggos: MutableList<Doggo> = arrayListOf()) : RecyclerView.Adapter<DoggoViewHolder>() {
```
The difference here is, we need a `MutableList` as `List` in Kotlin is immutable. Since we start by initialising the adapter and then we load the list of dogs asynchronously, the list will need to be modified afterwards. Also notice that doggos param is no longer mandatory. If no doggos is passed to the constructor, the list is initialized with a default value (here an empty list).

Then, we call the getDoggos method inside viewModel and add an observable wich once the doggos value is updated, we call the adapters `displayDoggos()` method. You will get compilation errors since that method does not exist. Put the cursor in the red area, press Alt + Enter and select the `Create member function DoggosAdapter.displayDoggos` press enter several times and you will see how the empty method is created. Remove the TODO code. 

Now you will have to write the method which should do 3 things: delete the content of doggos, add all the new doggos param (it) to doggos and finally we need to notify the adapter of the change.

5) Image loading

We will use the [Glide](https://github.com/bumptech/glide) library to display the images. The JSON object only contains the url with the image location but it still needs to be downloaded and used for the image view.
In `DoggosAdapter` replace the line image.setImageResource(doggo.image) by

Run the app and press `see more doggos` button, what do you see? oh no the images are all stretched :scream:
This can be fixed easily: Open `layout_doggo_item.xml` and replace set the `scaleType` to `centerInside`.
Run the app again and press the button, you will see how the images are loaded little by little when you scroll the list.

Our app starts to look more and more like a real app, doesn't it?

If you want to practice more you can do the next codelab: https://codelabs.developers.google.com/codelabs/kotlin-android-training-internet-images/index.html?index=..%2F..android-kotlin-fundamentals#0
