package com.example.store.features.dashboard.data


data class SpecialOffers (

	val id : Int,
	val title : String,
	val image : String,
	val start_price : Int,
	val end_price : Int,
	val off_percent : Int,
	val start_off_price : Int,
	val end_off_price : Int
)