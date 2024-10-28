package com.example.lib_network.service.model


data class Rover (

    var id          : Int?               = null,
    var name        : String?            = null,
    var landing_date : String?            = null,
    var launch_date  : String?            = null,
    var status      : String?            = null,
    var max_sol      : Int?               = null,
    var max_date     : String?            = null,
    var total_photos : Int?               = null,
    var cameras     : ArrayList<Cameras> = arrayListOf()

)