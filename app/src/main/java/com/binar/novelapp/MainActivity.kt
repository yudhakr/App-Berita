package com.binar.novelapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.novelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvNovel: RecyclerView
    private val list = ArrayList<Novel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvNovel = binding.rvNovel
        rvNovel.setHasFixedSize(true)
        list.addAll(listNovels)
        showRecyclerList()
    }

    private val listNovels: ArrayList<Novel>
        get() {
            val title = resources.getStringArray(R.array.title)
            val author = resources.getStringArray(R.array.author)
            val year = resources.getIntArray(R.array.year)
            val image = resources.getStringArray(R.array.image)
            val detail = resources.getStringArray(R.array.detail)
            val sinopsis = resources.getStringArray(R.array.sinopsis)

            val listNovel = ArrayList<Novel>()
            for (i in title.indices) {
                val novel = Novel(
                    title = title[i],
                    author = author[i],
                    year = year[i],
                    image = image[i],
                    detail = detail[i],
                    sinopsis = sinopsis[i]
                )
                listNovel.add(novel)
            }
            return listNovel
        }

    private fun showRecyclerList() {
        rvNovel.layoutManager = LinearLayoutManager(this)
        val listNovelAdapter = ListNovelAdapter(list)
        rvNovel.adapter = listNovelAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_about -> {
                Intent(this, AboutActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}