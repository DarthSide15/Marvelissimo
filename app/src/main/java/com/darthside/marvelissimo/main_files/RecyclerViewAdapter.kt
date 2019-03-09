package com.darthside.marvelissimo.main_files

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.darthside.marvelissimo.R

import java.util.ArrayList

class RecyclerViewAdapter(id : ArrayList<Int>, nameTitles: ArrayList<String>, images: ArrayList<String>, private val context: Context, isSeries : Boolean) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var id = arrayListOf<Int>()
    private var nameTitles = arrayListOf<String>()
    private var images = arrayListOf<String>()
    private var isSeries = false

    init {
        this.id = id
        this.nameTitles = nameTitles
        this.images = images
        this.isSeries = isSeries
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d(TAG, "onBindViewHolder: called")

        Glide.with(context)
            .asBitmap()
            .load(images[i])
            .into(viewHolder.image)

        viewHolder.nameTitle.text = nameTitles[i]
        viewHolder.parentLayout.setOnClickListener {
            Log.d(TAG, "onClick: clicked on " + nameTitles[i])
            Toast.makeText(context, nameTitles[i], Toast.LENGTH_SHORT).show()
            // Load CharacterDetails or SeriesDetails
            if (isSeries) {
                val intent = Intent(this.context, SeriesDetailsActivity::class.java)
                intent.putExtra("id", id[i])
                this.context.startActivity(intent)
            }else{
                val intent = Intent(this.context,CharacterDetailsActivity:: class.java)
                intent.putExtra("id",id[i])
                this.context.startActivity(intent)
            }

        }

        viewHolder.favouriteButton.setOnClickListener {
            Log.d(TAG, "Favourite button pressed")
            Toast.makeText(context, "Favourite button pressed on ${nameTitles[i]}", Toast.LENGTH_SHORT).show()


        }

    }

    override fun getItemCount(): Int {
        return nameTitles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var image: ImageView = itemView.findViewById(R.id.image)
        internal var nameTitle: TextView = itemView.findViewById(R.id.name_title)
        internal var favouriteButton: Button = itemView.findViewById(R.id.favourite_button)
        internal var parentLayout: RelativeLayout = itemView.findViewById(R.id.parent_layout)

    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }
}
