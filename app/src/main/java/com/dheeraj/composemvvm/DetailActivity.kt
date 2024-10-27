package com.dheeraj.composemvvm

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dheeraj.composemvvm.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var url = intent.extras?.get("detail_image_url")
        var detail_photo_id = intent.extras?.get("detail_photo_id")
        var detail_sol = intent.extras?.get("detail_sol")
        var detail_earth_date = intent.extras?.get("detail_earth_date")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.BLACK
        }

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Glide.with(this)
            .load(url)
            .placeholder(R.mipmap.ic_photo_holder)
            .error(R.mipmap.ic_photo_holder)
            .into(binding.ivRecipeItemImage)

        binding.tvName.text = "Photo ID: " + detail_photo_id
        binding.tvCaption.text = "Sol: " + detail_sol
        binding.tvCameraName.text = "Earth Date: " +detail_earth_date




    }


}