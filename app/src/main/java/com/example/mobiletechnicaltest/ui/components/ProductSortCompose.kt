package com.example.mobiletechnicaltest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mobiletechnicaltest.ui.plp.ProductListUiState
import com.example.mobiletechnicaltest.ui.plp.ProductListViewModel

/**
 * Compose entry point hosted inside [com.example.mobiletechnicaltest.ui.plp.ProductListFragment]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSortCompose(
    modifier: Modifier = Modifier,
    onCardSelected: (route: String) -> Unit = {}
) {

    val viewModel: ProductListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is ProductListUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is ProductListUiState.Empty -> {
                Text(text = "No hay productos", modifier = Modifier.align(Alignment.Center))
            }
            is ProductListUiState.Error -> {
                Text(
                    text = (uiState as ProductListUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is ProductListUiState.Success -> {
                val products = (uiState as ProductListUiState.Success).products

                Column(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        columns = GridCells.Fixed(2)
                    ) {
                        items(products) { product ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable { onCardSelected(product.id.toString()) }
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    AsyncImage(
                                        model = product.imageUrl,
                                        contentDescription = product.title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp),
                                        contentScale = ContentScale.Fit
                                    )

                                    Text(
                                        text = product.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )

                                    Row(
                                        modifier = Modifier.padding(top = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        StarRating(rating = product.rating)
                                        Text(
                                            text = "(${product.ratingCount})",
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(start = 4.dp)
                                        )
                                    }

                                    Text(
                                        text = "$${product.price}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = { showBottomSheet = true }
                    ) {
                        Text(text = "Opciones de ordenado")
                    }
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                // Contenido del BottomSheet
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = "Ordenar por",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalDivider()
                    
                    SortOption.entries.forEach { option ->
                        ListItem(
                            headlineContent = { Text(option.label) },
                            modifier = Modifier.clickable {
                                viewModel.sort(option)
                                showBottomSheet = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetailCompose(idProduct: Int) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Detalles del producto ID: $idProduct",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Esta es una vista previa simplificada del detalle.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductSortComposePreview() {
    ProductSortCompose()
}
