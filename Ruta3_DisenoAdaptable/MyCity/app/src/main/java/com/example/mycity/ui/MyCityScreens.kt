package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.LocalCityDataProvider
import com.example.mycity.model.Category
import com.example.mycity.model.Place
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun CategoryListScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            dimensionResource(R.dimen.padding_medium)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(categories, key = { it.id }) { category ->
            CategoryCard(category = category, onClick = { onCategoryClick(category) })
        }
    }
}

@Composable
private fun CategoryCard(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(category.imageRes),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            Column(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(category.nameRes),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(category.descriptionRes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun PlaceListScreen(
    places: List<Place>,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            dimensionResource(R.dimen.padding_medium)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        items(places, key = { it.id }) { place ->
            PlaceCard(place = place, onClick = { onPlaceClick(place) })
        }
    }
}

@Composable
private fun PlaceCard(
    place: Place,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = stringResource(place.nameRes),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(place.addressRes),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun PlaceDetailScreen(
    place: Place,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Image(
            painter = painterResource(place.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        )
        Text(
            text = stringResource(place.nameRes),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
        )
        Text(
            text = stringResource(place.addressRes),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.card_text_vertical_space))
        )
        Text(
            text = stringResource(place.descriptionRes),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
fun PlaceListAndDetail(
    places: List<Place>,
    selectedPlace: Place?,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxSize()) {
        PlaceListScreen(
            places = places,
            onPlaceClick = onPlaceClick,
            modifier = Modifier.width(320.dp)
        )
        if (selectedPlace != null) {
            PlaceDetailScreen(
                place = selectedPlace,
                modifier = Modifier.weight(1f)
            )
        } else {
            EmptyDetail(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun EmptyDetail(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.select_a_place),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    MyCityTheme {
        CategoryListScreen(
            categories = LocalCityDataProvider.categories,
            onCategoryClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun PlaceListAndDetailPreview() {
    MyCityTheme {
        PlaceListAndDetail(
            places = LocalCityDataProvider.placesFor(1),
            selectedPlace = LocalCityDataProvider.placesFor(1).first(),
            onPlaceClick = {}
        )
    }
}
