package com.monday.recyclerviewexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val map = (1 until 20).map { Data(it) }
        recyclerView = findViewById(R.id.recycler_view)
        val adapter = Adapter()
        adapter.data = map
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        findViewById<View>(R.id.notify_dataset_changed).setOnClickListener {
            flipRotation(adapter)
            adapter.notifyDataSetChanged()
        }

        findViewById<View>(R.id.item_changed).setOnClickListener {
            flipRotation(adapter)
            adapter.notifyItemChanged(0)
        }

        findViewById<View>(R.id.item_changed_payload).setOnClickListener {
            flipRotation(adapter)
            adapter.notifyItemChanged(0, Unit)
        }

    }

    private fun flipRotation(adapter: Adapter) {
        adapter.data[0].rotated = adapter.data[0].rotated.not()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.open_diff_util) {
            val intent = Intent(this, DiffUtilMainActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}