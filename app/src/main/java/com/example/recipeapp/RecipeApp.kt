package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.recipeState
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route  ){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewState ,navigatetoDetail ={
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.description.route)
            } )

        }
        composable(route = Screen.description.route){
                val category = navController.previousBackStackEntry?.savedStateHandle?.
                get<Category>("cat")?:Category("","","","")
            description(category = category)
        }
    }
}
