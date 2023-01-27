package br.eng.pedro_mendes.shopping_list.ui.add_shopping_list_item

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.shopping_list.databinding.ActivityAddShoppingListItemBinding

class AddShoppingListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddShoppingListItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddShoppingListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}