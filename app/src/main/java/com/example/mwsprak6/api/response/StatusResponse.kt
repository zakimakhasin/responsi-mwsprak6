package com.example.mwsprak6.api.response

import com.google.gson.annotations.SerializedName

data class StatusResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
