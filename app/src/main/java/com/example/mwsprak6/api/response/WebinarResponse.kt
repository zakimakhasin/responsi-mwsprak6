package com.example.mwsprak6.api.response

import com.google.gson.annotations.SerializedName

data class WebinarResponse(

	@field:SerializedName("data")
	val data: List<Participant>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class Participant(

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@field:SerializedName("prodi")
	val prodi: String
)
