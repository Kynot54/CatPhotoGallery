package com.example.catphotogallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catphotogallery.DetailActivity

class TileFragment constructor() : Fragment() {
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView: RecyclerView = inflater.inflate(
            R.layout.recycler_view,
            container, false
        ) as RecyclerView
        val adapter: ContentAdapter =
            ContentAdapter(recyclerView.context, DataInitializer.getData())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        return recyclerView
    }

    class ContentViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView
        var description: TextView? = null

        init {
            image = itemView.findViewById(R.id.tile_image)
            name = itemView.findViewById(R.id.tile_name)
            //description = itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener { view ->
                val context: Context = view.getContext()
                val intent: Intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.Companion.EXTRA_POSITION, adapterPosition)
                context.startActivity(intent)
            }
        }
    }

    class ContentAdapter constructor(
        var context: Context,
        var namedLocations: List<NamedLocation?>?
    ) : RecyclerView.Adapter<ContentViewHolder>() {
        public override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ContentViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_tile, parent, false
            )
            return ContentViewHolder(view)
        }

        public override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
            val namedLocation: NamedLocation? = namedLocations!![position]
            if (namedLocation != null) {
                holder.name.text = namedLocation.getName()
            }
            //holder.description.setText(namedLocation.getDescription());
            if (namedLocation != null) {
                holder.image.setImageResource(namedLocation.getImageID())
            }
        }

        public override fun getItemCount(): Int {
            return namedLocations!!.size
        }
    }
}