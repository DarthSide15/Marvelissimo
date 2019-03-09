package com.darthside.marvelissimo.main_files

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import com.darthside.marvelissimo.R

class SeriesDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_details)

        var text : TextView = findViewById(R.id.text_000)

        val passedId = intent.getIntExtra("id", 0)

        text.text = "$passedId"



/*        for (id in intent.getIntegerArrayListExtra("id")) {
            var idText = TextView(this)
            idText.text = "$id"

            layout.addView(idText)

        }*/


    }
}
