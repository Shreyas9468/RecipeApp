package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    init {
        fetchRecipes()
    }
    data class RecipeState(
        val isLoading: Boolean = false,
        val recipes: List<Category> = emptyList(),
        val error: String? = null
    )
    private val _recipeState = mutableStateOf(RecipeState())
    val recipeState: State<RecipeState> = _recipeState

    private fun fetchRecipes(){
        viewModelScope.launch {
            try {
                val recipes = recipeService.getCategories()
                _recipeState.value = _recipeState.value.copy(
                    isLoading = false,
                    recipes = recipes.categories,
                    error = null
                )
            }
            catch (e: Exception) {
                _recipeState.value = _recipeState.value.copy(
                    isLoading = false,
                    recipes = emptyList(),
                    error = e.message
                )
            }
        }
    }
}