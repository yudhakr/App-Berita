package com.binar.novelapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binar.novelapp.databinding.ActivityDetailNovelBinding
import com.bumptech.glide.Glide

class DetailNovelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNovelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNovelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.detail_novel)
        val dataNovel = intent.getParcelableExtra<Novel>(DETAIL_NOVEL) as Novel
        Glide.with(this)
            .load(dataNovel.image)
            .into(binding.ivNovel)

        binding.apply {
            tvTitle.text = dataNovel.title
            tvDesc.text = dataNovel.detail
            tvSinopsisDesc.text = dataNovel.sinopsis
        }
        binding.btnShare.setOnClickListener {
            share(getString(R.string.share, dataNovel.title))
        }
    }

    private fun share(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }
    
    companion object {
        const val DETAIL_NOVEL = "detail_novel"
    }
}
