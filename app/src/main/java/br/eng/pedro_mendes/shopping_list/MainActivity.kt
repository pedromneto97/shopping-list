package br.eng.pedro_mendes.shopping_list

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.shopping_list.databinding.ActivityMainBinding
import br.eng.pedro_mendes.shopping_list.ui.add_shopping_list_item.AddShoppingListItemActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                //TODO Handle result
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        setupAddShoppingListButton()
    }

    private fun setupAddShoppingListButton() {
        binding.fabAddShoppingListItem.setOnClickListener {
            activityResultLauncher.launch(Intent(this, AddShoppingListItemActivity::class.java))
        }
    }

}