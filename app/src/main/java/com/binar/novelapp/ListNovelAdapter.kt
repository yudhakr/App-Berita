package com.binar.novelapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.binar.novelapp.databinding.ItemNovelBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


class ListNovelAdapter(private val listNovel: ArrayList<Novel>) :
    RecyclerView.Adapter<ListNovelAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemNovelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(novel: Novel) {
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide.with(binding.root)
                .load(novel.image)
                .transition(withCrossFade(factory))
                .placeholder(ColorDrawable(Color.BLACK))
                .into(binding.ivNovel)

            binding.tvTitle.text = novel.title
            binding.tvAuthor.text = novel.author
            binding.tvYear.text = novel.year.toString()

            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Kamu Memilih ${listNovel[absoluteAdapterPosition].title}",
                    Toast.LENGTH_SHORT
                ).show()

                Intent(binding.root.context, DetailNovelActivity::class.java).also {
                    it.putExtra(DetailNovelActivity.DETAIL_NOVEL,novel)
                    binding.root.context.startActivity(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemNovelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val novel = listNovel[position]
        holder.bindView(novel)
    }

    override fun getItemCount(): Int = listNovel.size
}