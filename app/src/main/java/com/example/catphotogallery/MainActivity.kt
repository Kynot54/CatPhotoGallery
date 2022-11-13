package com.example.catphotogallery

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import androidx.viewpager.widget.ViewPager
import com.example.catphotogallery.DetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity() : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        configureViewPager(viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        //drawer & nav view
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer)

        //get action bar & replace up arrow w/ menu drawer icon
        val actionBar = supportActionBar
        if (actionBar != null) {
            val icon = VectorDrawableCompat.create(
                resources,
                R.drawable.ic_base_menu_black_24, theme
            )
            actionBar.run {
                setHomeAsUpIndicator(icon)
                setDisplayHomeAsUpEnabled(true)
            }
        }
            NavigationView.OnNavigationItemSelectedListener { item ->
                item.isChecked = true
                var position: Int? = null
                if (item.itemId == R.id.action_newest) {
                    position = 3
                } else if (item.itemId == R.id.action_oldest) {
                    position = 0
                } else if (item.itemId == R.id.action_quantity) {
                    position = 7
                }
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.Companion.EXTRA_POSITION, position)
                startActivity(intent)
                drawerLayout?.run {
                    intent.putExtra(DetailActivity.Companion.EXTRA_POSITION, position)
                    startActivity(intent)
                    closeDrawers()
                }
                true
            }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val popup = PopupMenu(this@MainActivity, fab)
                popup.menuInflater.inflate(R.menu.sort_menu, popup.menu)
                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem): Boolean {
                        if (item.itemId == R.id.sort_oldest) DataInitializer.setSort(("oldest")) else if (item.itemId == R.id.sort_youngest) DataInitializer.setSort(
                            "youngest"
                        ) else if (item.itemId == R.id.sort_alphabetical) DataInitializer.setSort("alphabetical") else if (item.itemId == R.id.sort_rAlphabetical) DataInitializer.setSort(
                            "rAlphabetical"
                        ) else return false
                        configureViewPager(viewPager)
                        return true
                    }
                })
                popup.show()
            }
        })
    }

    private fun configureViewPager(viewPager: ViewPager) {
        val adapter = CustomPagerAdapter(supportFragmentManager)
        adapter.addFragment(ListFragment(), "List")
        adapter.addFragment(TileFragment(), "Tile")
        adapter.addFragment(CardFragment(), "Card")
        viewPager.adapter = adapter
    }

    internal class CustomPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(
        (fm)!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        private val fragments: MutableList<Fragment> = ArrayList()
        private val titles: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout!!.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}