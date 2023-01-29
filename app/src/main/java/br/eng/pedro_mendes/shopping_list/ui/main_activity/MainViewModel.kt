package br.eng.pedro_mendes.shopping_list.ui.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.eng.pedro_mendes.shopping_list.data.ShoppingListItem
import br.eng.pedro_mendes.shopping_list.data.repository.ShoppingListRepository

class MainViewModel : ViewModel() {
    private val shoppingListRepository = ShoppingListRepository()
    private val innerShoppingList = MutableLiveData<List<ShoppingListItem>>()

    val shoppingList: LiveData<List<ShoppingListItem>> = innerShoppingList

    fun initialize() = updateShoppingList()

    private fun updateShoppingList() {
        innerShoppingList.value = shoppingListRepository.get()
    }

    fun setCheck(int: Int, isChecked: Boolean) {
        shoppingListRepository.setChecked(int, isChecked)
        updateShoppingList()
    }

    fun clear() {
        shoppingListRepository.clear()
        updateShoppingList()
    }

    fun add(shoppingListItem: ShoppingListItem) {
        shoppingListRepository.add(shoppingListItem)
        updateShoppingList()
    }

    fun deleteItem(index: Int) {
        shoppingListRepository.deleteItem(index)
        updateShoppingList()
    }
}