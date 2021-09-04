package com.example.patrimoniomap.barSearch

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.patrimoniomap.R

class barSearch : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bar_search)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.test_array))
        lv_listView.adapter = adapter
        lv_listView.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, id ->
            Toast.makeText(applicationContext, parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
        lv_listView.emptyView = tv_emptyTextView


        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_bar_search,menu)

        val search = menu?.findItem(R.id.search_bar)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search something!"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun OnQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun OnQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)

    }
}