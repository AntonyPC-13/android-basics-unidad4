package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    val categoryId: Int,
    @StringRes val nameRes: Int,
    @StringRes val addressRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
