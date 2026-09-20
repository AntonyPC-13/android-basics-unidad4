package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Category
import com.example.mycity.model.Place

object LocalCityDataProvider {

    val categories = listOf(
        Category(
            id = 1,
            nameRes = R.string.category_restaurants,
            descriptionRes = R.string.category_restaurants_description,
            imageRes = R.drawable.ic_category_restaurants
        ),
        Category(
            id = 2,
            nameRes = R.string.category_coffee,
            descriptionRes = R.string.category_coffee_description,
            imageRes = R.drawable.ic_category_coffee
        ),
        Category(
            id = 3,
            nameRes = R.string.category_parks,
            descriptionRes = R.string.category_parks_description,
            imageRes = R.drawable.ic_category_parks
        ),
        Category(
            id = 4,
            nameRes = R.string.category_museums,
            descriptionRes = R.string.category_museums_description,
            imageRes = R.drawable.ic_category_museums
        ),
        Category(
            id = 5,
            nameRes = R.string.category_shopping,
            descriptionRes = R.string.category_shopping_description,
            imageRes = R.drawable.ic_category_shopping
        )
    )

    val places = listOf(
        Place(1, 1, R.string.place_ceviche_name, R.string.place_ceviche_address, R.string.place_ceviche_description, R.drawable.ic_category_restaurants),
        Place(2, 1, R.string.place_chifa_name, R.string.place_chifa_address, R.string.place_chifa_description, R.drawable.ic_category_restaurants),
        Place(3, 1, R.string.place_pollada_name, R.string.place_pollada_address, R.string.place_pollada_description, R.drawable.ic_category_restaurants),
        Place(4, 2, R.string.place_cafe_centro_name, R.string.place_cafe_centro_address, R.string.place_cafe_centro_description, R.drawable.ic_category_coffee),
        Place(5, 2, R.string.place_cafe_barranco_name, R.string.place_cafe_barranco_address, R.string.place_cafe_barranco_description, R.drawable.ic_category_coffee),
        Place(6, 2, R.string.place_cafe_libreria_name, R.string.place_cafe_libreria_address, R.string.place_cafe_libreria_description, R.drawable.ic_category_coffee),
        Place(7, 3, R.string.place_reserva_name, R.string.place_reserva_address, R.string.place_reserva_description, R.drawable.ic_category_parks),
        Place(8, 3, R.string.place_kennedy_name, R.string.place_kennedy_address, R.string.place_kennedy_description, R.drawable.ic_category_parks),
        Place(9, 3, R.string.place_malecon_name, R.string.place_malecon_address, R.string.place_malecon_description, R.drawable.ic_category_parks),
        Place(10, 4, R.string.place_larco_name, R.string.place_larco_address, R.string.place_larco_description, R.drawable.ic_category_museums),
        Place(11, 4, R.string.place_mali_name, R.string.place_mali_address, R.string.place_mali_description, R.drawable.ic_category_museums),
        Place(12, 4, R.string.place_catacumbas_name, R.string.place_catacumbas_address, R.string.place_catacumbas_description, R.drawable.ic_category_museums),
        Place(13, 5, R.string.place_mercado_name, R.string.place_mercado_address, R.string.place_mercado_description, R.drawable.ic_category_shopping),
        Place(14, 5, R.string.place_artesania_name, R.string.place_artesania_address, R.string.place_artesania_description, R.drawable.ic_category_shopping),
        Place(15, 5, R.string.place_centro_comercial_name, R.string.place_centro_comercial_address, R.string.place_centro_comercial_description, R.drawable.ic_category_shopping)
    )

    fun placesFor(categoryId: Int): List<Place> = places.filter { it.categoryId == categoryId }

    val defaultCategory = categories.first()
}
