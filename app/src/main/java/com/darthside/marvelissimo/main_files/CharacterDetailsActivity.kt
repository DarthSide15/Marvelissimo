package com.darthside.marvelissimo.main_files

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.darthside.marvelissimo.R
import com.darthside.marvelissimo.api.APICaller

class CharacterDetailsActivity : AppCompatActivity() {

    private val apiCaller = APICaller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val passedId = intent.getIntExtra("id", 0)

        var characterimagePh : ImageView? = findViewById(R.id.image_ph)
        var namePh : TextView? = findViewById(R.id.character_title_ph)
        var descriptionPh : TextView? = findViewById(R.id.character_description)
        var seriesListLayout : LinearLayout = findViewById(R.id.series_list_layout)
        var urlButton : Button = findViewById(R.id.url_button)


        apiCaller.getCharacterById ({

            runOnUiThread {
                for (c in it) {
                    //imagePh = s.thumbnail
                    namePh?.text = c.name
                    descriptionPh?.text = c.description
                    var wikiUrl = c.urls[1].url
                    urlButton.text = "More details"
                    urlButton.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(wikiUrl))
                        startActivity(intent)
                    }

                    for (c in c.series.items) {
                        var seriesName = c.name
                        var textView = TextView(this)
                        textView.setTextColor(ContextCompat.getColor(this, R.color.colorText))
                        textView.text = seriesName
                        seriesListLayout.addView(textView)
                    }

                }
            }
        }, passedId)



    }

}
