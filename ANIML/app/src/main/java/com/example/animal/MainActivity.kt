package com.example.animal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animal.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var ANAdapter: AnimalAdapter
    private val DaoAnimal by lazy { AnimalDatabase.getDatabase(this).AnimalDao() }
    lateinit var  animalData: List<EntityAnimal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        animalData = listOf()

        retrieveAnimals()

        Binding.fab.setOnClickListener { AddingPage() }
    }

     fun deleteAnimal(animal: EntityAnimal) {
        CoroutineScope(Dispatchers.IO).launch {

            DaoAnimal.deleteAnimal(animal)
        }
    }

//    fun updateAnimal(animal: EntityAnimal) {
//        CoroutineScope(Dispatchers.IO).launch {
//
//            DaoAnimal.updateAnimal(animal)
//
//        }
//    }

    private fun AddingPage() {
        val intent = Intent(this, AddingPage::class.java)
        startActivity(intent)
    }

    fun retrieveAnimals() {
        CoroutineScope(Dispatchers.IO).launch {
            val animalDatabase = async { DaoAnimal.getAllAnimals() }.await()
            if (animalDatabase != null) {
                withContext(Dispatchers.Main) {
                    animalData = animalDatabase
                    Binding.AnimalRV.adapter = AnimalAdapter(animalData, this@MainActivity)
                    Binding.AnimalRV.layoutManager = LinearLayoutManager(this@MainActivity)
                    Binding.AnimalRV.adapter!!.notifyDataSetChanged()
                    Log.d("databaseInformation","$animalData")
                }
            }else{
                Toast.makeText(this@MainActivity, "No InformationBack", Toast.LENGTH_SHORT).show()
            }
        }
    }
}