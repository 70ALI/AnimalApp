package com.example.animal


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animal.databinding.ItemRowBinding


class AnimalAdapter(val listOfAnimals: List<EntityAnimal>, val activity: MainActivity) :
    RecyclerView.Adapter<AnimalAdapter.AnimalAdapterView>() {
     class AnimalAdapterView(val Binding: ItemRowBinding) :
        RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalAdapterView {
        return AnimalAdapterView(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: AnimalAdapterView, position: Int) {
        val animal = listOfAnimals[position]
        holder.Binding.apply {

            deleteBtn.setOnClickListener {

                activity.deleteAnimal(animal)
            }
//            editBtn.setOnClickListener {
//                activity.updateAnimal(animal)
//
//            }
            TypeTV.text = animal.type
            ConditionTV.text = animal.condition
            SummaryTV.text = animal.summary
            PriceTV.text = animal.price
            Log.d("InSideTheAdapter","$animal")
        }
    }

    override fun getItemCount(): Int {
        return listOfAnimals.size
    }
}
