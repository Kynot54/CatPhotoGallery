package com.example.catphotogallery

object DataInitializer {
    private val data: MutableList<NamedLocation?> = ArrayList<NamedLocation?>()
    private var sort = "name"
    fun setSort(newSort: String) {
        sort = newSort
    }

    fun getData(): List<NamedLocation?> {
        when (sort) {
            "youngest" -> data.sortWith(comparator =   { n11: NamedLocation, n12: NamedLocation -> n11.age - n12.age } as (NamedLocation?, NamedLocation?) -> Int)
            "oldest" -> data.sortWith(comparator =  { n11: NamedLocation, n12: NamedLocation -> n12.age - n11.age } as (NamedLocation?, NamedLocation?) -> Int)
            "alphabetical" -> data.sortWith(comparator = { n11: NamedLocation, n12: NamedLocation ->
                n11.name.compareTo(
                    n12.name
                )
            } as (NamedLocation?, NamedLocation?) -> Int)
            "rAlphabetical" -> data.sortWith(comparator = { n11: NamedLocation, n12: NamedLocation ->
                n12.name.compareTo(
                    n11.name
                )
            } as (NamedLocation?, NamedLocation?) -> Int)
            else -> {}
        }
        return data
    }

    init {
        data.add(
            NamedLocation(
                "Orange Tabby",
                "Orange Tabby Cat that stares into Camera",
                "Southern Orange County, CA",
                14,
                "Yes",
                R.drawable.boi_gazes_into_your_soul
            )
        )
        data.add(
            NamedLocation(
                "Silver Tabby on my HW",
                "Silver Tabby Cat lays on my Calculus Two Homework",
                "Southern Orange County, CA",
                2,
                "Yes",
                R.drawable.boi_hw
            )
        )
        data.add(
            NamedLocation(
                "Long Hair Tabby in Basket",
                "Long Hair Tabby Sleeping in a Basket",
                "Inland Empire, CA",
                12,
                "No",
                R.drawable.boi_in_basket
            )
        )
        data.add(
            NamedLocation(
                "Long Hair Tabby",
                "Long Hair Tabby Looking Out Window",
                "Inland Empire, CA",
                12,
                "No",
                R.drawable.boi_in_window
            )
        )
        data.add(
            NamedLocation(
                "Silver Tabby Laying on Ground",
                "Silver Tabby that Expands on the Floor",
                "Southern Orange County, CA",
                2,
                "Yes",
                R.drawable.fatboi_landscape
            )
        )
        data.add(
            NamedLocation(
                "Russian Blue",
                "Russian Blue that Lays on my Bed and took my spot while I was Reading",
                "Southern Orange County, CA",
                3,
                "Yes",
                R.drawable.tiny_bop_while_reading
            )
        )
        data.add(
            NamedLocation(
                "Russian Blue Sleeping on Table",
                "Russian Blue Sleeping on Dinning Room Table Sleeping",
                "Southern Orange County, CA",
                3,
                "Yes",
                R.drawable.tiny_bopper
            )
        )
        data.add(
            NamedLocation(
                "Two Cats",
                "Two Cats Curled Up in a Basket",
                "Northern Orange County, CA",
                10,
                "No",
                R.drawable.two_bois
            )
        )
    }
}