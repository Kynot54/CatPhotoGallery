package com.example.catphotogallery

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.util.*

class DetailActivity : AppCompatActivity() {
    private val locations = DataInitializer.getData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        val position = intent.getIntExtra(EXTRA_POSITION, 0)
        val namedLocation = locations!![position]
        val imageView = findViewById<ImageView>(R.id.details_image)
        namedLocation?.let { imageView.setImageResource(it.imageID) }
        collapsingToolbarLayout.title = namedLocation!!.name
        val description = findViewById<TextView>(R.id.detail_description)
        description.text = namedLocation.description
        val location = findViewById<TextView>(R.id.detail_location)
        location.text = namedLocation.location
        val age = findViewById<TextView>(R.id.detail_age)
        age.text = namedLocation.age.toString()
        val myCat = findViewById<TextView>(R.id.detail_myCat)
        myCat.text = namedLocation.myCat
    }

    companion object {
        const val EXTRA_POSITION = "position"
    }
}