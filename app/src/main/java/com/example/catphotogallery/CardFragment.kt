package com.example.catphotogallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catphotogallery.DetailActivity
import com.google.android.material.snackbar.Snackbar

class CardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recyclerView: RecyclerView = inflater.inflate(
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
            image = itemView.findViewById(R.id.card_image)
            name = itemView.findViewById(R.id.card_name)
            description = itemView.findViewById(R.id.card_desc)
            itemView.setOnClickListener { view ->
                val context = view.context
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_POSITION, adapterPosition)
                context.startActivity(intent)
            }
            val mapButton = itemView.findViewById<Button>(R.id.card_map_button)
            val likeButton = itemView.findViewById<ImageButton>(R.id.card_like_button)
            val shareButton = itemView.findViewById<ImageButton>(R.id.card_share_button)
            mapButton.setOnClickListener(CardOnClickListener())
            likeButton.setOnClickListener(CardOnClickListener())
            shareButton.setOnClickListener(CardOnClickListener())
        }
    }

    class CardOnClickListener : View.OnClickListener {
        var msg = ""
        override fun onClick(view: View) {
            when (view.id) {
                R.id.card_map_button -> msg =
                    "You selected the Map Button"
                R.id.card_like_button -> msg =
                    "You selected the Like Button"
                R.id.card_share_button -> msg =
                    "You selected the Share Button"
            }
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
        }
    }

    class ContentAdapter(var context: Context, var namedLocations: List<NamedLocation?>?) :
        RecyclerView.Adapter<ContentViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_card, parent, false
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
                holder.image.setImageResource(namedLocation.getImageID())
            }
        }

        override fun getItemCount(): Int {
            return namedLocations!!.size
        }
    }
}