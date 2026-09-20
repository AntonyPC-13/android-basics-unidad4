package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.LocalCityDataProvider
import com.example.mycity.model.Category
import com.example.mycity.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun selectCategory(category: Category) {
        _uiState.update { current ->
            current.copy(
                currentCategory = category,
                placesInCategory = LocalCityDataProvider.placesFor(category.id),
                currentPlace = null
            )
        }
    }

    fun selectPlace(place: Place) {
        _uiState.update { current -> current.copy(currentPlace = place) }
    }

    fun clearPlace() {
        _uiState.update { current -> current.copy(currentPlace = null) }
    }
}
