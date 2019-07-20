# Day 5 - Data Storage

This course is designed for students who have completed [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day1%20-%20Kotlin%20Basics/),  [Day 2](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%202%20-%20Android%20Basics/),  [Day 3](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%203%20-%20Multi%20Screen%20Apps/) and [Day 4](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%204%20-%20Networking/).

In this course, you will learn about the importance of data persistence when building an Android app. We'll introduce you to the fundamentals of SQL, the programming language needed to interact with an SQLite relational database. SQLite is a commonly used method to store large sets of data locally on an Android device.
If you haven't used SQLite or if you need to refresh your memory, please read this [introductory document first](https://developer.android.com/courses/extras/sql-primer).
During the workshop, we will follow this [CODELAB](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/index.html?index=..%2F..index#0).
 

## What you'll learn

- SQL fundamentals
- Using [Room](https://developer.android.com/topic/libraries/architecture/room) to store and retrieve data in the device's SQLite database.

## What you must know already

This codelab is written for programmers and assumes you know either Java or Kotlin. If you are an experienced programmer and you are adept at reading code, you will likely be able to follow this codelab even if you don't have much experience with Kotlin.

If you do not know any Kotlin, check this [Kotlin Basic Syntax](https://kotlinlang.org/docs/reference/basic-syntax.html) link it will teach you what you need to know to move forward with the workshop. 

If you missed the past day check out [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day1%20-%20Kotlin%20Basics/), [Day 2](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%202%20-%20Android%20Basics/) and [Day 3](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%203%20-%20Multi%20Screen%20Apps/) and [Day 4](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%204%20-%20Networking/).

To work through this codelab, you will need a computer that can run Android Studio (or already has Android Studio installed). Install Android Studio following [this instructions](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Installation.md)

## Homework
### Activities cleanup

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
Now you need to add a listener to the FAB so when clicked, the user is taken to the `AddDoggoActivity`.

### Repository
If you check `DoggoViewModel` class, you will see we are calling the api directly. This is not the best practive and also if we want to gets dogs from different places, we would not be able to. Lets add another layer, the repository.
First, create a data package. Inside add `DoggosRepository` kotlin class and also move the networking package there (by dragging it).
This is the repository code:
```
package com.womenwhocode.workshop.doggoapp.data

import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.data.networking.DogApi
import kotlinx.coroutines.Deferred

class DoggosRepository {

    fun getDoggos(): Deferred<List<Doggo>> {
        return DogApi.retrofitService.getDoggos()
    }
}
```
Open DoggoViewModel and replace the line:

val getDoggosDeferred = DogApi.retrofitService.getDoggos() by

val getDoggosDeferred = repository.getDoggos()

You will see that repository is not found. This is because we need to add it to the constructor. The class signature will become:

class DoggoViewModel(private val repository: DoggosRepository = DoggosRepository()): ViewModel() {

Now you can run your project again, make sure the dogs are still displayed.

### Doggo
The `Doggo` class has to be modified, the same object that is used to get data from the API will be reused for the database Entity. It's ok for our simple example. Here is how the Doggo data class will look like now:

```
package com.womenwhocode.workshop.doggoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Doggo(
    @PrimaryKey(autoGenerate = true) val doggoId: Long = 0,
    val name: String,
    val age: String,
    val size: String,
    val url: String
)
```
Notice how we use doggoId as the primary key and since we do not care about the id, we let the database autogenerate it. It will be set to 0 by default and we cannot call it simply id because the api response already contains an id which is a string. 

### Database
inside the data package, create a database package. Here you will need to add `DoggoDao` and `DoggosRoomDatabase`. Use the codelab code to help you to create these classes.

The DoggosRoomDatabase only needs one method:

`fun getDatabase(context: Context): DoggosRoomDatabase {}`

`DoggoDao` will have a method to retrieve all the Doggos and one to insert a list of Doggos.

### Repository part2
The Repository needs to be modified to the following code:
```
package com.womenwhocode.workshop.doggoapp.data

import android.content.Context
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.data.database.DoggoDao
import com.womenwhocode.workshop.doggoapp.data.database.DoggosRoomDatabase
import com.womenwhocode.workshop.doggoapp.data.networking.DogApi

class DoggosRepository(application: Context) {

    private val dogsDao: DoggoDao = DoggosRoomDatabase.getDatabase(application).doggoDao()

    fun getAllDoggos(): List<Doggo> {
        return dogsDao.getAllDoggos()
    }

    suspend fun downloadDoggos(): List<Doggo> {
        val dogs = DogApi.retrofitService.getDoggos().await()
        dogsDao.insertAll(dogs)
        return getAllDoggos()
    }
}
```
Here we are initialising the database, getting the list of all dogs form the database and when needed download dogs calling the API and insert them in the database. 

### ViewModel
Our ViewModel needs to extend AndroidViewModel instead of ViewModel because we need Application for the database.
Here is the new signature:

`class DoggoViewModel(application: Application) : AndroidViewModel(application)`

We need to add the repository: 

`private var repository: DoggosRepository = DoggosRepository(application)`

The method `getDoggos()` will become:
```
fun getDoggos(): MutableLiveData<List<Doggo>> {
        coroutineScope.launch {
            doggos.value = loadDoggos()
        }
        return doggos
    }
```

Here is the full code of `DoggoViewModel`

```
class DoggoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: DoggosRepository = DoggosRepository(application)
    private var doggos: LiveData<List<Doggo>>
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        doggos = repository.allDoggos
    }

    fun getDoggos(): LiveData<List<Doggo>> {
        return doggos
    }

    fun loadDoggos() {
        coroutineScope.launch {
            repository.downloadDoggos()
        }
    }
}
```

That way, if the database is empty, we will load the dogs from the API. Once the list of dogs is in the database, the API will not be queried again so we save mobile data. In a real life app, there would be a mechanism by which we will check if the local data is too old/outdated and reload.

You can now run the app and it should load a bit faster after the first time.
