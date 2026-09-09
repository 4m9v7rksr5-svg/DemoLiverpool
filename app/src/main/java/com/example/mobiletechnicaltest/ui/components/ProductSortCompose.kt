package com.example.mobiletechnicaltest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(10)
        {
              Card(modifier = Modifier.padding(16.dp).clickable(true)
              {
                  onCardSelected("IdProduct")
              })
              {
                  Column() {
                      Text("Imagen de referencia")
                      Text(text = "Precio")
                  }
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


