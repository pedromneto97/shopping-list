package br.eng.pedro_mendes.shopping_list.data

data class ShoppingListItem(
    var name: String,
    var check: Boolean,
    var quantity: Double,
    var measure: Measure,
)
