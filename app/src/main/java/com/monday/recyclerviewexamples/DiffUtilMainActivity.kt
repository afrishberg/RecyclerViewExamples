package com.monday.recyclerviewexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DiffUtilMainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diffutil_activity_main)
        val map = (1 until 20).map { Data(it) }.toMutableList()
        recyclerView = findViewById(R.id.recycler_view)
        val adapter = DiffListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.submitList(map)


        findViewById<View>(R.id.submit_list).setOnClickListener {
            flipRotation(adapter)
        }
    }

    private fun flipRotation(adapter: DiffListAdapter) {
        val newList = adapter.currentList.mapIndexed { i, data ->
            if (i == 0) data.copy(rotated = data.rotated.not()) else data
        }
        adapter.submitList(newList)
    }


}