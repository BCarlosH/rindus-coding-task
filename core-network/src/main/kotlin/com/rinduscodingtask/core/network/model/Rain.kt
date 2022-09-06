package com.rinduscodingtask.core.network.model

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val volumeLastThreeHours: Double
)
