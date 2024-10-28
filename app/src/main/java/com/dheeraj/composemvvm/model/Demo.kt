package com.dheeraj.composemvvm.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Demo(

    @SerializedName("id" ) var photoId        : Int?,
    var sol       : Int?,
    var camera    : Camera? = Camera(),
    var img_src    : String?,
    var earth_date : String?,
    var rover     : Rover?  = Rover()
): Serializable
