package br.eng.pedro_mendes.shopping_list.ui.add_shopping_list_item

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.shopping_list.R
import br.eng.pedro_mendes.shopping_list.data.Measure
import br.eng.pedro_mendes.shopping_list.data.ShoppingListItem
import br.eng.pedro_mendes.shopping_list.databinding.ActivityAddShoppingListItemBinding
import br.eng.pedro_mendes.shopping_list.ui.main_activity.MainActivity

class AddShoppingListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddShoppingListItemBinding
    private var measure: Measure = Measure.KG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddShoppingListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        setSaveListener()
        setRadioButtonListener()
    }

    private fun setRadioButtonListener() {
        binding.rgMeasure.setOnCheckedChangeListener { _, i ->
            measure = when (i) {
                R.id.rb_kg -> Measure.KG
                R.id.rb_gram -> Measure.GRAM
                R.id.rb_liter -> Measure.LITER
                R.id.rb_unity -> Measure.UNIT
                else -> Measure.KG
            }
            Log.i("MEASURE", measure.toString())
        }
    }

    private fun setSaveListener() {
        binding.buttonSave.setOnClickListener {
            saveShoppingListItem()
        }
    }

    private fun saveShoppingListItem() {
        validateProductName()
        validateQuantity()

        val productName = binding.editTextName.text.toString()
        val quantity = binding.editTextQuantity.text.toString().toIntOrNull()
        if (productName.isEmpty() || quantity == null) {
            return
        }

        Intent().apply {
            putExtra(
                MainActivity.RESULT_TAG,
                ShoppingListItem(
                    name = productName,
                    quantity = quantity,
                    measure = measure
                )
            )
            setResult(RESULT_OK, this)
        }
        finish()
    }


    private fun validateProductName() {
        val productName = binding.editTextName.text.toString()

        binding.textInputName.error = if (productName.isEmpty()) {
            getString(R.string.product_name_cannot_be_empty)
        } else {
            ""
        }
    }

    private fun validateQuantity() {
        val quantity = binding.editTextQuantity.text.toString()

        binding.textInputQuantity.error =
            if (quantity.isEmpty()) {
                getString(R.string.quantity_cannot_be_empty)
            } else if (quantity.toIntOrNull() == null) {
                getString(R.string.quantity_must_be_an_integer)
            } else {
                ""
            }
    }
}