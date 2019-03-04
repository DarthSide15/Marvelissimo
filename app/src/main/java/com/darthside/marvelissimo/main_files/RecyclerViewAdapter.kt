package com.darthside.marvelissimo.main_files

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.darthside.marvelissimo.R

import java.util.ArrayList

class RecyclerViewAdapter(characterNames: ArrayList<String>, characterImages: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var characterNames = arrayListOf<String>()
    private var characterImages = arrayListOf<String>()

    init {
        this.characterNames = characterNames
        this.characterImages = characterImages
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.character_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d(TAG, "onBindViewHolder: called")

        Glide.with(context)
            .asBitmap()
            .load(characterImages[i])
            .into(viewHolder.image)

        viewHolder.characterName.text = characterNames[i]
        viewHolder.parentLayout.setOnClickListener {
            Log.d(TAG, "onClick: clicked on " + characterNames[i])
            Toast.makeText(context, characterNames[i], Toast.LENGTH_SHORT).show()
            // Load CharacterDetails
        }

        viewHolder.favouriteButton.setOnClickListener {
            Log.d(TAG, "Favourite button pressed")
            Toast.makeText(context, "Favourite button pressed on ${characterNames[i]}", Toast.LENGTH_SHORT).show()
            // Add this character to favourites
        }

    }

    override fun getItemCount(): Int {
        return characterNames.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var image: ImageView = itemView.findViewById(R.id.character_image)
        internal var characterName: TextView = itemView.findViewById(R.id.character_name)
        internal var favouriteButton: Button = itemView.findViewById(R.id.favourite_button)
        internal var parentLayout: RelativeLayout = itemView.findViewById(R.id.parent_layout)

    }

    companion object {

        private const val TAG = "RecyclerViewAdapter"
    }
}
