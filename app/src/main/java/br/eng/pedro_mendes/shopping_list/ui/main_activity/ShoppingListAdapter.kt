package br.eng.pedro_mendes.shopping_list.ui.main_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.eng.pedro_mendes.shopping_list.R
import br.eng.pedro_mendes.shopping_list.data.Measure
import br.eng.pedro_mendes.shopping_list.data.ShoppingListItem
import br.eng.pedro_mendes.shopping_list.databinding.ShoppingListItemBinding

class ShoppingListAdapter(
    private val shoppingList: List<ShoppingListItem>,
    private val onCheckItem: (index: Int, isChecked: Boolean) -> Unit,
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    private lateinit var binding: ShoppingListItemBinding

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ShoppingListItem, index: Int) {
            setShoppingListItem(item = item)
            setOnCheckCallback(index)
        }

        private fun setOnCheckCallback(index: Int) {
            binding.checkShoppingItem.setOnCheckedChangeListener { _, value ->
                onCheckItem(index, value)
            }
        }

        private fun setShoppingListItem(item: ShoppingListItem) {
            binding.apply {
                textProductName.text = item.name
                textQuantity.text = itemView.context.getString(
                    R.string.text_quantity,
                    item.quantity,
                    getMeasureText(measure = item.measure)
                )
                checkShoppingItem.isChecked = item.check
            }
        }

        private fun getMeasureText(measure: Measure): String {
            val text = when (measure) {
                Measure.KG -> "KG"
                Measure.GRAM -> "g"
                Measure.UNIT -> itemView.context.getString(R.string.unit)
                Measure.LITER -> "L"
            }

            return text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ShoppingListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = shoppingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(item = shoppingList[position], position)
}