package com.example.mobiletechnicaltest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobiletechnicaltest.ui.components.ProductDetailCompose
import com.example.mobiletechnicaltest.ui.components.ProductSortCompose
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold { innerPadding ->
                NavigationRoute(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun NavigationRoute(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "Products") {
        composable("Products") {
            ProductSortCompose(modifier)
            {
                navController.navigate("Detail/${it}")
            }
        }

        composable("Detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }))
        {  backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ProductDetailCompose(id,modifier,onBack = {navController.popBackStack()})
        }
    }
}