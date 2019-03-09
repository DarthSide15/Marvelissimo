package com.darthside.marvelissimo.main_files

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView
import com.darthside.marvelissimo.R

import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        var text : TextView = findViewById(R.id.characterDetails_TextView)

        val passedId = intent.getIntExtra("id", 0)

        text.text = "$passedId"
    }

}
