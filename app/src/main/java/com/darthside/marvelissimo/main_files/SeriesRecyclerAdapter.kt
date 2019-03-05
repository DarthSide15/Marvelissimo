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

class SeriesRecyclerAdapter(seriesTitles: ArrayList<String>, seriesImages: ArrayList<String>, private val context: Context) :
RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var seriesNames = arrayListOf<String>()
    private var seriesImages = arrayListOf<String>()

    init {
        this.seriesNames = seriesTitles
        this.seriesImages = seriesImages
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.series_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d(TAG, "onBindViewHolder: called")

        Glide.with(context)
            .asBitmap()
            .load(seriesImages[i])
            .into(viewHolder.image)

        viewHolder.characterName.text = seriesNames[i]
        viewHolder.parentLayout.setOnClickListener {
            Log.d(TAG, "onClick: clicked on " + seriesNames[i])
            Toast.makeText(context, seriesNames[i], Toast.LENGTH_SHORT).show()
            // Load CharacterDetails
        }

        viewHolder.favouriteButton.setOnClickListener {
            Log.d(TAG, "Favourite button pressed")
            Toast.makeText(context, "Favourite button pressed on ${seriesNames[i]}", Toast.LENGTH_SHORT).show()
            // Add this character to favourites
        }

    }

    override fun getItemCount(): Int {
        return seriesNames.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var image: ImageView = itemView.findViewById(R.id.series_image)
        internal var seriesTitle: TextView = itemView.findViewById(R.id.series_title)
        internal var favouriteButton: Button = itemView.findViewById(R.id.series_favourite_button)
        internal var parentLayout: RelativeLayout = itemView.findViewById(R.id.series_parent_layout)

    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }
}