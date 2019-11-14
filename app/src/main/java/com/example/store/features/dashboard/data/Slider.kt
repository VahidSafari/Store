package com.example.store.features.dashboard.data

data class Slider (

	val id : Int,
	val title : String,
	val image : String,
	val tag : Int,
	val link : String
) {
	fun toTopSliderEntity() =
		TopSliderEntity(
			id,
			image
		)
}