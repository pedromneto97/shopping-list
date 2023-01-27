package br.eng.pedro_mendes.shopping_list.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingListItem(
    var name: String,
    var check: Boolean = false,
    var quantity: Int,
    var measure: Measure,
) : Parcelable
