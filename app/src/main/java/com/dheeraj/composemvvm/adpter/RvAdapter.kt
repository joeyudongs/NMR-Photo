package com.dheeraj.composemvvm.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheeraj.composemvvm.R
import com.dheeraj.composemvvm.databinding.ItemRvBinding
import com.dheeraj.composemvvm.model.Demo

class RvAdapter (private var photoList: List<Demo>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun updateData(photosData: List<Demo>){
        photoList = photosData
        notifyDataSetChanged()
    }


    fun sortData(isAscend: Boolean){
        if (isAscend) {

            photoList.sortedBy { it.photoId }
        }else{
            photoList.sortedByDescending { it.photoId }

        }

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = photoList[position]
        holder.binding.apply{

            tvName.text = "Id: ${currentItem.photoId}"

            ivRecipeItemImage.apply {
                Glide.with(context)
                    .load(currentItem.img_src)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(this)
            }
        }
    }
}