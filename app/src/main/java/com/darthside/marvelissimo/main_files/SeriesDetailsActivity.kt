package com.darthside.marvelissimo.main_files

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.darthside.marvelissimo.R
import com.darthside.marvelissimo.api.APICaller
class SeriesDetailsActivity : AppCompatActivity() {

    private val apiCaller = APICaller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_details)

        val passedId = intent.getIntExtra("id", 0)

        var imagePh : ImageView? = findViewById(R.id.image_ph)
        val titlePh : TextView? = findViewById(R.id.title_ph)
        val descriptionPh : TextView? = findViewById(R.id.series_description)
        val startYearPh : TextView? = findViewById(R.id.start_year_ph)
        val endYearPh : TextView? = findViewById(R.id.end_year_ph)
        val characterListLayout : LinearLayout = findViewById(R.id.character_list_layout)

        apiCaller.getSeriesById({


            runOnUiThread {
                for (s in it) {
                    //imagePh = s.thumbnail
                    titlePh?.text = s.title
                    descriptionPh?.text = s.description
                    startYearPh?.text = s.startYear.toString()
                    endYearPh?.text = s.endYear.toString()

                    for (c in s.characters.items) {
                        val characterName = c.name
                        val textView = TextView(this)
                        textView.setTextColor(ContextCompat.getColor(this, R.color.colorText))
                        textView.text = characterName
                        characterListLayout.addView(textView)
                    }

                }
            }

        }, passedId)


    }
}
