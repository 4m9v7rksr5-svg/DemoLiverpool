package com.example.mobiletechnicaltest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.mobiletechnicaltest.domain.model.Product
import com.example.mobiletechnicaltest.ui.pdp.ProductDetailState
import com.example.mobiletechnicaltest.ui.pdp.ProductDetailViewModel
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
                          ProductItem(onCardSelected = onCardSelected, product = product,false,modifier)
                        }
                        }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = { showBottomSheet = true }
                    ) {
                        Text(text = "Ordenar por...")
                    }
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
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
fun ProductItem(onCardSelected: (route: String) -> Unit, product: Product,isDetail: Boolean = false,modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Card(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { onCardSelected(product.id.toString()) }
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                modifier = modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )
        }


        Text(
            text = product.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(top = 8.dp)
        )

        Row(
            modifier = modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StarRating(rating = product.rating)
            Text(
                text = "(${product.ratingCount})",
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(start = 4.dp)
            )
        }

        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(top = 4.dp)
        )
        if (isDetail)
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(top = 8.dp)
            )
    }
}



@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }) {
            Icon(Icons.Default.Remove, contentDescription = "Restar")
        }
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        IconButton(onClick = { onQuantityChange(quantity + 1) }) {
            Icon(Icons.Default.Add, contentDescription = "Sumar")
        }
    }
}

@Composable
fun ProductDetailCompose(idProduct: Int, modifier: Modifier, onBack: () -> Unit) {
    val viewModel: ProductDetailViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()
    var quantity by remember { mutableIntStateOf(1) }

    LaunchedEffect(idProduct) {
        viewModel.loadProduct(idProduct)
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is ProductDetailState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ProductDetailState.Error -> {
                Text(
                    text = (uiState as ProductDetailState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is ProductDetailState.Success -> {
                val product = (uiState as ProductDetailState.Success).product
                Column(modifier = Modifier.fillMaxSize()) {
                    ProductItem({}, product, true, modifier)

                    QuantitySelector(
                        quantity = quantity,
                        onQuantityChange = { quantity = it }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onClick = { }
                    ) {
                        Text(text = "Añadir al carrito")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = { onBack.invoke() }
                    ) {
                        Text(text = "Regresar a productos")
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductSortComposePreview() {
    ProductSortCompose()
}
