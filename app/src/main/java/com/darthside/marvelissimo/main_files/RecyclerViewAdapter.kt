package com.darthside.marvelissimo.main_files

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.darthside.marvelissimo.R
import com.darthside.marvelissimo.entities.ListItem

import java.util.ArrayList

class RecyclerViewAdapter(listItems : ArrayList<ListItem>, private val context: Context, isSeries : Boolean) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var listItems = arrayListOf<ListItem>()
    private var isSeries = false

    init {
        Log.d("test", "list items count: " + listItems.size)
        this.listItems = listItems
        this.isSeries = isSeries
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        var item = listItems[i]
        Glide.with(context)
            .asBitmap()
            .load(item.image)
            .into(viewHolder.image)

        viewHolder.nameTitle.text = item.name
        viewHolder.parentLayout.setOnClickListener {
            Log.d(TAG, "onClick: clicked on " + item.name)
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
            // Load CharacterDetails or SeriesDetails
            if (isSeries) {
                val intent = Intent(this.context, SeriesDetailsActivity::class.java)
                intent.putExtra("id", item.id)
                this.context.startActivity(intent)
            }else{
                val intent = Intent(this.context,CharacterDetailsActivity:: class.java)
                intent.putExtra("id",item.id)
                this.context.startActivity(intent)
            }

        }

        viewHolder.favouriteButton.setOnClickListener {
            Log.d(TAG, "Favourite button pressed")
            Toast.makeText(context, "Favourite button pressed on ${item.name}", Toast.LENGTH_SHORT).show()

            if(isSeries) {

            }

        }

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var image: ImageView = itemView.findViewById(R.id.image_ph)
        internal var nameTitle: TextView = itemView.findViewById(R.id.name_title)
        internal var favouriteButton: Button = itemView.findViewById(R.id.favourite_button)
        internal var parentLayout: RelativeLayout = itemView.findViewById(R.id.parent_layout)

    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }
}
