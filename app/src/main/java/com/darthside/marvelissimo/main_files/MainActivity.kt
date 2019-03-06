package com.darthside.marvelissimo.main_files

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.darthside.marvelissimo.R.id.nav_home
import android.support.v4.app.Fragment
import com.darthside.marvelissimo.fragments.*


class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        SeriesFragment.OnFragmentInteractionListener,
        CharactersFragment.OnFragmentInteractionListener,
        FavouriteFragment.OnFragmentInteractionListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var seriesFragment: SeriesFragment
    private lateinit var charactersFragment: CharactersFragment
    private lateinit var favouriteFragment: FavouriteFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.darthside.marvelissimo.R.layout.activity_main)

        homeFragment = HomeFragment.newInstance("p1", "p2")
        seriesFragment = SeriesFragment.newInstance("p1", "p2")
        charactersFragment = CharactersFragment.newInstance("p1", "p2")
        favouriteFragment = FavouriteFragment.newInstance("p1", "p2")

        setLaunchFragment(homeFragment)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
            com.darthside.marvelissimo.R.string.navigation_drawer_open,
            com.darthside.marvelissimo.R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun setLaunchFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(com.darthside.marvelissimo.R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.darthside.marvelissimo.R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            com.darthside.marvelissimo.R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            nav_home -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(com.darthside.marvelissimo.R.id.container, homeFragment)
                    .addToBackStack(homeFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            com.darthside.marvelissimo.R.id.nav_series -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(com.darthside.marvelissimo.R.id.container, seriesFragment)
                    .addToBackStack(seriesFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            com.darthside.marvelissimo.R.id.nav_characters -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(com.darthside.marvelissimo.R.id.container, charactersFragment)
                    .addToBackStack(charactersFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            com.darthside.marvelissimo.R.id.nav_favourites -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(com.darthside.marvelissimo.R.id.container, favouriteFragment)
                    .addToBackStack(favouriteFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("Darthside", "On Fragment Interaction")
    }
}
