package com.example.mobiletechnicaltest.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onSortSelected: (SortOption) -> Unit
) {
    Button(
        modifier = Modifier.padding(vertical = 8.dp),
        onClick = {
            // TODO Candidate: open the sort options and invoke onSortSelected(option)
        }
    ) {
        Text(text = "Ordenar por")
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductSortComposePreview() {
    ProductSortCompose(onSortSelected = {})
}
