package com.example.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.ui.utils.MyCityContentType

enum class MyCityScreen(@StringRes val title: Int) {
    Categories(title = R.string.app_name),
    Places(title = R.string.screen_places),
    Detail(title = R.string.screen_detail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Expanded -> MyCityContentType.LIST_AND_DETAIL
        else -> MyCityContentType.LIST_ONLY
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Categories.name) {
                CategoryListScreen(
                    categories = uiState.categories,
                    onCategoryClick = { category ->
                        viewModel.selectCategory(category)
                        navController.navigate(MyCityScreen.Places.name)
                    }
                )
            }
            composable(route = MyCityScreen.Places.name) {
                if (contentType == MyCityContentType.LIST_AND_DETAIL) {
                    PlaceListAndDetail(
                        places = uiState.placesInCategory,
                        selectedPlace = uiState.currentPlace,
                        onPlaceClick = { viewModel.selectPlace(it) }
                    )
                } else {
                    PlaceListScreen(
                        places = uiState.placesInCategory,
                        onPlaceClick = { place ->
                            viewModel.selectPlace(place)
                            navController.navigate(MyCityScreen.Detail.name)
                        }
                    )
                }
            }
            composable(route = MyCityScreen.Detail.name) {
                uiState.currentPlace?.let { place ->
                    PlaceDetailScreen(place = place)
                }
            }
        }
    }
}
