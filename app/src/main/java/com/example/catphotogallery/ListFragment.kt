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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catphotogallery.DetailActivity

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = inflater.inflate(
            R.layout.recycler_view,
            container, false
        ) as RecyclerView
        val adapter = ContentAdapter(recyclerView.context, DataInitializer.getData())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return recyclerView
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView
        var description: TextView

        init {
            image = itemView.findViewById(R.id.list_image)
            name = itemView.findViewById(R.id.list_name)
            description = itemView.findViewById(R.id.list_desc)
            itemView.setOnClickListener { view ->
                val context = view.context
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.Companion.EXTRA_POSITION, adapterPosition)
                context.startActivity(intent)
            }
        }
    }

    class ContentAdapter(var context: Context, var namedLocations: List<NamedLocation?>?) :
        RecyclerView.Adapter<ContentViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_list, parent, false
            )
            return ContentViewHolder(view)
        }

        override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
            val namedLocation = namedLocations!![position]
            if (namedLocation != null) {
                holder.name.text = namedLocation.getName()
            }
            if (namedLocation != null) {
                holder.description.text = namedLocation.getDescription()
            }
            if (namedLocation != null) {
                holder.image.setImageBitmap(
                    BitmapUtil.createCircleBitmap(
                        context,
                        namedLocation.getImageID()
                    )
                )
            }
        }

        override fun getItemCount(): Int {
            return namedLocations!!.size
        }
    }
}