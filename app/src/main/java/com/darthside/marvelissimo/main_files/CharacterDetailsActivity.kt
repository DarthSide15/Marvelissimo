package com.darthside.marvelissimo.main_files

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
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


        apiCaller.getCharacterById ({

            runOnUiThread {
                for (c in it) {
                    //imagePh = s.thumbnail
                    namePh?.text = c.name
                    descriptionPh?.text = c.description

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
