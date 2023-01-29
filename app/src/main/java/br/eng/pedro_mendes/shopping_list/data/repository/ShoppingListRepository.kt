package br.eng.pedro_mendes.shopping_list.data.repository

import android.util.Log
import br.eng.pedro_mendes.shopping_list.data.ShoppingListItem

class ShoppingListRepository {
    private val _shoppingList = mutableListOf<ShoppingListItem>()


    fun add(shoppingListItem: ShoppingListItem) {
        _shoppingList.add(shoppingListItem)
    }

    fun setChecked(index: Int, value: Boolean) {
        _shoppingList[index].check = value
        Log.i("ShoppingListRepository", "Index: $index - $value")
    }

    fun clear() = _shoppingList.clear()

    fun get(): List<ShoppingListItem> = _shoppingList.toList()
}