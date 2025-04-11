package com.feesBus.ui.users.models

import androidx.annotation.DrawableRes

data class PointerPoint(
    @DrawableRes val image: Int,
    val head: String,
    val body: String,
)