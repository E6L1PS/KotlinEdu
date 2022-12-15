package com.example.recyclerviewexp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var persons: List<Person>
    private lateinit var recyclerView: RecyclerView
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileString =
            application.assets.open("persons.json").bufferedReader().use { it.readText() }
        val gson = GsonBuilder().create()
        persons = gson.fromJson(fileString, Array<Person>::class.java).toList()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(persons)
        recyclerView.adapter = adapter
        radioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbSortByName -> recyclerView.adapter = MyAdapter(persons.sortedBy { it.name })
                R.id.rbSortByNumber -> recyclerView.adapter = MyAdapter(persons.sortedBy { it.phoneNumber })
                R.id.rbSortBySex -> recyclerView.adapter = MyAdapter(persons.sortedBy { it.sex })
                else -> recyclerView.adapter = MyAdapter(persons.sortedBy { it.name })
            }
        }
    }

}