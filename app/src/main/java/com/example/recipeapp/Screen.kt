package com.example.recipeapp


sealed  class Screen(val route : String) {
    object RecipeScreen : Screen("RecipeScreen")
    object description : Screen("Recipedescription")
}