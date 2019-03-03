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
import android.widget.TextView
import com.darthside.marvelissimo.fragments.CharactersFragment
import com.darthside.marvelissimo.fragments.FavouriteFragment
import com.darthside.marvelissimo.fragments.HomeFragment
import com.darthside.marvelissimo.fragments.SeriesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.darthside.marvelissimo.models.Response
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import com.darthside.marvelissimo.R.id.nav_home
import com.darthside.marvelissimo.models.Character
import android.support.v4.app.Fragment
import com.darthside.marvelissimo.R


class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        SeriesFragment.OnFragmentInteractionListener,
        CharactersFragment.OnFragmentInteractionListener,
        FavouriteFragment.OnFragmentInteractionListener {

    lateinit var homeFragment: HomeFragment
    lateinit var seriesFragment: SeriesFragment
    lateinit var charactersFragment: CharactersFragment
    lateinit var favouriteFragment: FavouriteFragment

    private val httpTag = "HTTP"
    private val ts = "1"
    private val apiKey = "174943a97b8c08a00a80d1ed425d9ed1"
    private val hash = "8b36d2a14cd3a4cec60c30e9f70b8ab3"
    private var url = ""
    private val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.darthside.marvelissimo.R.layout.activity_main)

        homeFragment = HomeFragment.newInstance("p1", "p2")
        seriesFragment = SeriesFragment.newInstance("p1", "p2")
        charactersFragment = CharactersFragment.newInstance("p1", "p2")
        favouriteFragment = FavouriteFragment.newInstance("p1", "p2")

        setLaunchFragment(homeFragment)
        setSupportActionBar(toolbar)

        var nameInput = "spider-man"

        // TODO: set characterName to users input
        // TODO: make request with characterName as argument
        getCharacter(nameInput)
        // TODO: Update UI elements with the data from the Character object

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
            com.darthside.marvelissimo.R.string.navigation_drawer_open,
            com.darthside.marvelissimo.R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun getCharacter(name : String) {

        var character : Character
        url = "https://gateway.marvel.com/v1/public/characters?name=$name&ts=$ts&apikey=$apiKey&hash=$hash"
        Log.d(httpTag, "Attempting request")
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val response = response.body()?.string()
                println("Response body: $response")

                val gson = GsonBuilder().create()
                val jsonData = gson.fromJson(response, Response::class.java)

                val text : TextView

                println("Json Data: $jsonData")
                if (jsonData.data.results.isNotEmpty()) {
                    character = jsonData.data.results.first()
                    println("Characters found: ${jsonData.data.results.size}")
                    println(character)

                    // TODO: put jsonData.name in name_placeholder
                    // TODO: put jsonData.description in description_placeholder

                    val name = jsonData.data.results.first().name
                    val description = jsonData.data.results.first().description
                    val namePlaceholder = findViewById<TextView>(R.id.name_placeholder)
                    val descriptionPlaceholder = findViewById<TextView>(R.id.description_placeholder)


                    runOnUiThread {
                        descriptionPlaceholder.text = description
                        namePlaceholder.text = name
                    }

                } else {
                    println("Characters found: ${jsonData.data.results.size}")
                    println("Could not find this character")
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request")
            }
        })

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
