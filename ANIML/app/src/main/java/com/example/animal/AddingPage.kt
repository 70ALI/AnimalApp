package com.example.animal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animal.databinding.ActivityAddingPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddingPage : AppCompatActivity() {
    lateinit var Binding: ActivityAddingPageBinding

    var animalList = listOf<EntityAnimal>()
    private val DaoAnimal by lazy { AnimalDatabase.getDatabase(this).AnimalDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityAddingPageBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        Binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } // Fix

        CoroutineScope(Dispatchers.IO).launch {
            animalList = DaoAnimal.getAllAnimals()
        }
        Binding.apply {
            submitBtn.setOnClickListener {
                var type = addTypeET.text.toString()
                var condition = addConditionET.text.toString()
                var summary = addSummaryET.text.toString()
                var price = addPriceET.text.toString()

                var animal = EntityAnimal(0, type, condition, summary, price)
                CoroutineScope(Dispatchers.IO).launch {
                    DaoAnimal.addAnimal(animal)
                    val intent = Intent(this@AddingPage, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
