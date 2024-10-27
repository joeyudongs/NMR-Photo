package com.dheeraj.composemvvm.model

data class Rover (

    var id          : Int?               = null,
    var name        : String?            = null,
    var landingDate : String?            = null,
    var launchDate  : String?            = null,
    var status      : String?            = null,
    var maxSol      : Int?               = null,
    var maxDate     : String?            = null,
    var totalPhotos : Int?               = null,
    var cameras     : ArrayList<Cameras> = arrayListOf()

)