package com.tanveer.assignmenttask2

data class AdapterDataClass(
    var item: String? = "",
    var Quantity: Int? = 0,
){
    override fun toString(): String {
        return "$item"
    }
}

