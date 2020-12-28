package com.example.dogs.breeds.ui.breedphotos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbasualdo.dogs.databinding.ItemGridBreedBinding
import com.squareup.picasso.Picasso

class BreedImageAdapter(private val images: MutableList<String> = mutableListOf()): RecyclerView.Adapter<BreedImageAdapter.BreedImageViewHolder>() {
    private var onClickListener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: ((String) -> Unit)?){
        this.onClickListener = listener
    }

    fun setDataSet(newDataSet: List<String>){
        images.clear()
        images.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGridBreedBinding.inflate(inflater, parent,false)
        return BreedImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedImageViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(images[holder.adapterPosition])
        }
    }

    override fun getItemCount() = images.size

    class BreedImageViewHolder(val binding: ItemGridBreedBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(image: String){
            Picasso.get().load(image).into(binding.ivBreed)
        }
    }
}