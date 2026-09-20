package com.example.mycity.ui

import com.example.mycity.data.LocalCityDataProvider
import com.example.mycity.model.Category
import com.example.mycity.model.Place

data class MyCityUiState(
    val categories: List<Category> = LocalCityDataProvider.categories,
    val currentCategory: Category = LocalCityDataProvider.defaultCategory,
    val placesInCategory: List<Place> = LocalCityDataProvider.placesFor(LocalCityDataProvider.defaultCategory.id),
    val currentPlace: Place? = null
)
