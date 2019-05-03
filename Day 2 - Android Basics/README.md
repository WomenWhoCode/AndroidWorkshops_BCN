# Day 2 - Android Basics: User Interface and User Input

Learn the basics of Android and Kotlin programming, and take the first step on your journey to becoming an Android developer! This course is designed for students who are new to programming, and want to learn how to build Android apps. 

By the end of this course, you will have learned how to build an app’s layout and then practiced those skills by making a form to collect user input.

During this second workshop we will follow [this codelab](http://xariti.pandiandcode.com/codelabs/wwcbcn-android-interface-inputs/#0).

## What you'll learn

- How to built app's layout on Android Studio
- How to make your app interact with a user

## What you must know already

This codelab is written for programmers and assumes you know either Java or Kotlin. If you are an experienced programmer and you are adept at reading code, you will likely be able to follow this codelab even if you don't have much experience with Kotlin.

If you do not know any Kotlin, check this [Kotlin Basic Syntax](https://kotlinlang.org/docs/reference/basic-syntax.html) link it will teach you what you need to know to move forward with the workshop. 

If you missed the past day check out [Day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Day%201%20-%20Kotlin%20Basics/README.md) and follow the codelab to learn basics of Kotlin and Android Development. 

To work through this codelab, you will need a computer that can run Android Studio (or already has Android Studio installed). Install Android Studio following [these instructions](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/blob/master/Installation.md).


## Homework
You will use your homework app or use [the solution from the day 1](https://github.com/WomenWhoCode/AndroidWorkshops_BCN/tree/master/Day%201%20-%20Kotlin%20Basics/DoggoApp) We recommend you use your own proyect, but feel free to check our solution

### Exercise 1
We are going to work with forms! But first we need to create a new Activity `AddDoggoActivity.kt` with its layout `activity_add_doggo.xml` and a way to open it.
Just like we did in the previous homework we are going to add a new button and the code needed in `MainActivity.kt` and `activity_main.xml`

Once you have it, open the `activity_add_doggo.xml` and be creative! Desing your form with the data you want to store
for your dog. You can choose the layout you want ( `LinearLayout` which is less powerful or  `ConstraintLayout`  that is a bit trikier)

* Add min of 2 fields, some of the fields needs to be restricted by type
* Add 1 button for saving the info at the end of the form
* When the save button is clicked we need to check that the fields have the correct info ( they are not empty)


### Exercise 2
Lets take a photo of our fav dog, for this we need :
* Add 1 button for taking the photo
* Use a imageView for showing the photo we are going to take
* ✓Tip: Taking a photo can be difficult but we thought that is cool to have it, so we are going to extra help you in this part first [check the docs](https://developer.android.com/training/camera/photobasics) to understand a bit what is happening
and second you will only need to copy this code and pasted it in `AddDoggoActivity.kt`

* First add the permissions to your app, so the user will be asked if they want our app to use the camera:
```
<manifest ... >
    <uses-feature android:name="android.hardware.camera"
                  android:required="true" />
    ...
</manifest>
``` 

* Second in our `AddDoggoActivity.kt` we need to asign this function to our button for making photos. In this code we do an special intent and open the Android System app for the camera

```
val REQUEST_IMAGE_CAPTURE = 1

private fun dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
}
``` 

* Third paste this code in  `AddDoggoActivity.kt` and this code recives the photo when the camera is finished. At the last line we see an imageView ( this is the imageView of our layout) and we set the photo with `setImageBitmap`
```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        val imageBitmap = data.extras.get("data") as Bitmap
        imageView.setImageBitmap(imageBitmap)
    }
}
```

### Exercise 3
Add another activity and layout for showing all the data we gathered with the form you can use `ShowDoggoActivity.kt` and `activity_show_doggo.xml`
For this we need to share the info from `AddDoggoActivity.kt` with this new activity. For this we can store data in the intent. This is a hit of how to do it :
```
  private fun displayDogInfo(name: String, age: String, size: String, photo: Bitmap) {
        var intent = Intent(this, ShowDoggoActivity::class.java)
        intent.putExtra(NAME_FIELD,name)
        ...
        intent.putExtra(PHOTO_FIELD,photo)
        startActivity(intent)
    }
 ```
 In the new activity we only need to show the data :) 


### Final result

<img src="https://user-images.githubusercontent.com/923280/56064626-da33e600-5d72-11e9-9c18-86f96cdae23e.gif" width="100" />

