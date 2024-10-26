package com.dheeraj.composemvvm.model

data class Demo(
    var id        : Int?    ,
    var sol       : Int?    ,
    var camera    : Camera? = Camera(),
    var img_src    : String? ,
    var earth_date : String? ,
    var rover     : Rover?  = Rover()
)
