package com.dheeraj.composemvvm.adpter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheeraj.composemvvm.DetailActivity
import com.dheeraj.composemvvm.R
import com.dheeraj.composemvvm.databinding.ItemRvBinding
import com.example.lib_network.service.model.Demo

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

        var currentList: List<Demo>

        if (isAscend) {

            currentList = photoList.sortedBy { it.photoId }
        }else{
            currentList = photoList.sortedByDescending { it.photoId }

        }

        photoList = currentList

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photoList[position]
        holder.binding.apply{

            /*tvName.text = "Rover: ${item.rover?.name.toString()}"
            tvCaption.text = "LandingDate: ${item.rover?.landing_date.toString()}"
            tvCameraName.text = "Status: ${item.rover?.status}"*/

            tvEarthDate.text = "Earth Date: " + item.earth_date
            tvPhotoId.text = "Photo Id: " + item.photoId

            ivRecipeItemImage.apply {
                Glide.with(context)
                    .load(item.img_src)
                    .placeholder(R.mipmap.ic_photo_holder)
                    .error(R.mipmap.ic_photo_holder)
                    .into(this)
            }

            holder.binding.root.setOnClickListener{
                val intent = Intent(holder.binding.root.context,DetailActivity::class.java)
                intent.putExtra("detail_image_url",item.img_src)
                intent.putExtra("detail_photo_id",item.photoId)
                intent.putExtra("detail_sol",item.sol)
                intent.putExtra("detail_earth_date",item.earth_date)
                holder.binding.root.context.startActivity(intent)
            }
            rlBtAnim.setOnClickListener {
                buttonAnimation.visibility = View.VISIBLE
                tvBt.visibility = View.GONE

                buttonAnimation.playAnimation()
                rlBtAnim.handler.postDelayed(
                    {

                        buttonAnimation.pauseAnimation()
                        buttonAnimation.visibility = View.GONE
                        tvBt.visibility = View.VISIBLE

                        val intent = Intent(holder.binding.root.context,DetailActivity::class.java)
                        intent.putExtra("detail_image_url",item.img_src)
                        intent.putExtra("detail_photo_id",item.photoId)
                        intent.putExtra("detail_sol",item.sol)
                        intent.putExtra("detail_earth_date",item.earth_date)
                        holder.binding.root.context.startActivity(intent)
                    }, 1000)

            }

        }
    }
}