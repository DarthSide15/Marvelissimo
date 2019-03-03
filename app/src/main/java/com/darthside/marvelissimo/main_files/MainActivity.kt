package com.darthside.marvelissimo.main_files

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import java.security.MessageDigest
import com.darthside.marvelissimo.R.id.nav_home
import com.darthside.marvelissimo.api.MarvelAPI


class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        SeriesFragment.OnFragmentInteractionListener,
        CharactersFragment.OnFragmentInteractionListener,
        FavouriteFragment.OnFragmentInteractionListener
{

    lateinit var homeFragment: HomeFragment
    lateinit var seriesFragment: SeriesFragment
    lateinit var charactersFragment: CharactersFragment
    lateinit var favouriteFragment: FavouriteFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.darthside.marvelissimo.R.layout.activity_main)
        setSupportActionBar(toolbar)
        authenticateUrl()
        var characterName = "Hawkeye"

        MarvelAPI().getCharacter(characterName)

/*        val service = RetroFitClientInstance.retrofitInstance?.create(GetCharactersService::class.java)
        val call = service?.getAllCharacters()
        call?.enqueue(object : Callback<Response> {

            override fun onResponse(call: Call<Response>, response: Response<Response>) {
                val body = response?.body()
                val result = body?.result
                var size = result?.size
                Log.d("URL", "Size: ${size.toString()}")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(applicationContext, "Error handling JSON", Toast.LENGTH_LONG).show()
            }
        })*/



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
            com.darthside.marvelissimo.R.string.navigation_drawer_open,
            com.darthside.marvelissimo.R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment.newInstance("p1", "p2")
        seriesFragment = SeriesFragment.newInstance("p1", "p2")
        charactersFragment = CharactersFragment.newInstance("p1", "p2")
        favouriteFragment = FavouriteFragment.newInstance("p1", "p2")

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

    private fun authenticateUrl() {
        val ts = "1"
        val publicKey = "174943a97b8c08a00a80d1ed425d9ed1"
        val privateKey = "0033be867dc3fdb7df59babb98fa5f55b2c7dbd8"

        val hexString = StringBuilder("")
        val md5 = MessageDigest.getInstance("MD5")
        md5.digest("$ts$privateKey$publicKey".toByteArray()).forEach {
            hexString.append(String.format("%02x", it))
        }
        val hash = hexString.toString()

        Log.d("", "Hash generated: $hash")
        Log.d("", "Timestamp set to $ts")
        Log.d("", "Public key is $publicKey")
    }
}
