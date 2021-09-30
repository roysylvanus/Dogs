package com.malinikali.dogapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.malinikali.dogapp.databinding.BreedLayoutBinding
import com.malinikali.dogapp.model.BreedResponseItem

class BreedAdapter:RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    private val diffUtilCallback = object : DiffUtil.ItemCallback<BreedResponseItem>(){
        override fun areItemsTheSame(
            oldItem: BreedResponseItem,
            newItem: BreedResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BreedResponseItem,
            newItem: BreedResponseItem
        ): Boolean {
            return newItem == oldItem
        }
    }
    private val differ = AsyncListDiffer(this,diffUtilCallback)
    var breedList:List<BreedResponseItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }


    inner class BreedViewHolder(val binding: BreedLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
       return BreedViewHolder(BreedLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val currentBreed = breedList[position]

        holder.binding.apply {
            tvDescription.text = currentBreed.description
            tvLifeSpan.text = currentBreed.lifeSpan
            tvName.text = currentBreed.name
            tvOrigin.text = currentBreed.origin
            try {
                imageView.load(currentBreed.image.url){
                    crossfade(true)
                    crossfade(1000)
                }
            }catch (e:Exception){
                Log.e("URL",e.message.toString())
            }

        }
    }

    override fun getItemCount() = breedList.size
}