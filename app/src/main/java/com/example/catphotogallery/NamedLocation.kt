package com.example.catphotogallery

class NamedLocation constructor(
    var name: String,
    var description: String,
    var location: String,
    var age: Int,
    var myCat: String,
    var imageID: Int
) {
    fun getName(): CharSequence? {
        return name
    }

    fun getDescription(): CharSequence? {
        return description
    }

    fun getLocation(): CharSequence? {
        return location;
    }

    @JvmName("getAge1")
    fun getAge(): Int {
        return age
    }

    @JvmName("getImageID1")
    fun getImageID(): Int {
        return imageID
    }

}