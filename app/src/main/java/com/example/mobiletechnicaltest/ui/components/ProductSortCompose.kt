package com.example.mobiletechnicaltest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mobiletechnicaltest.domain.model.Product
import com.example.mobiletechnicaltest.ui.plp.ProductListUiState
import com.example.mobiletechnicaltest.ui.plp.ProductListViewModel

/**
 * Compose entry point hosted inside [com.example.mobiletechnicaltest.ui.plp.ProductListFragment]
 * via a ComposeView, demonstrating Compose-in-Views interop.
 *
 * // TODO Candidate: replace this scaffold with the real sorting UI, e.g. a
 * // TODO Candidate: ModalBottomSheet / dialog listing the [SortOption] values.
 * // TODO Candidate: call [onSortSelected] when the user picks an option.
 */
@Composable
fun ProductSortCompose(
    modifier: Modifier = Modifier,
    onSortSelected: (SortOption) -> Unit,
    onCardSelected: (route:String) -> Unit ={}
) {
    val viewModel: ProductListViewModel = hiltViewModel()

    var productList : List<Product> = emptyList()
    viewModel.uiState.collectAsState().let {
        when(it.value) {
            ProductListUiState.Empty -> {}
            is ProductListUiState.Error -> {}
            ProductListUiState.Loading -> {}
            is ProductListUiState.Success -> {
                productList = (it.value as ProductListUiState.Success).products
            }
        }
        }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(productList.size)
        {
            Column() {
              Card(modifier = Modifier
                  .padding(16.dp)
                  .clickable(true)
                  {
                      onCardSelected("IdProduct")
                  })
              {
                      AsyncImage(
                          model = productList[it].imageUrl,
                          contentDescription = "",
                          modifier = Modifier
                              .size(88.dp),
                          contentScale = ContentScale.Crop
                      )
                  }
                Text("Rating ${productList[it].rating}")
                Text(text = "Precio : ${productList[it].price}")
              }
         }
    }
   Box(
       modifier.fillMaxSize(),
       contentAlignment = Alignment.BottomCenter,
   ){
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                onSortSelected(SortOption.PRICE_DESC)
            }
        ) {
            Text(text = "Ordenar por precio")
        }

}
}


@Composable
fun ProductDetailCompose(idProduct:Int)
{
    Card(modifier = Modifier.padding(16.dp))
    {
        Column() {
            Text("Detalles del producto + $idProduct")
            Text(text = "Precio")
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ProductSortComposePreview() {
    ProductSortCompose(onSortSelected = {})
}


