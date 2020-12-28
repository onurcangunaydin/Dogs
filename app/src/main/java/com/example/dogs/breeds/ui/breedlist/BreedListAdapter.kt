package com.example.dogs.breeds.ui.breedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pbasualdo.dogs.BR
import com.example.dogs.breeds.data.DogBreed
import com.pbasualdo.dogs.databinding.BreedListItemBinding

class BreedListAdapter(private val dogBreadsList: MutableList<DogBreed> = mutableListOf()) :RecyclerView.Adapter<BreedListViewHolder>(){
    private var onClickListener: ((DogBreed) -> Unit)? = null

    fun setOnClickListener(listener: ((DogBreed) -> Unit)?){
        this.onClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = BreedListItemBinding.inflate(inflater, parent, false)
        return BreedListViewHolder(dataBinding)
    }

    override fun getItemCount() = dogBreadsList.size

    override fun onBindViewHolder(holder: BreedListViewHolder, position: Int) {
        holder.bind(dogBreadsList[position])
        holder.dataBinding.root.setOnClickListener {
            onClickListener?.invoke(dogBreadsList[holder.adapterPosition])
        }

    }

    fun updateBreedList(newDataSet: List<DogBreed>) {
        dogBreadsList.clear()
        dogBreadsList.addAll(newDataSet)
        notifyDataSetChanged()
    }
}

class BreedListViewHolder constructor(val dataBinding:ViewDataBinding) :RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(dogBreed: DogBreed) {
        dataBinding.setVariable(BR.itemBreed, dogBreed)
        dataBinding.executePendingBindings()
    }
}