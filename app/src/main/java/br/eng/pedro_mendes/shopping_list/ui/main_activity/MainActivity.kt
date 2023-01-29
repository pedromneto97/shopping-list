package br.eng.pedro_mendes.shopping_list.ui.main_activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.eng.pedro_mendes.shopping_list.R
import br.eng.pedro_mendes.shopping_list.data.ShoppingListItem
import br.eng.pedro_mendes.shopping_list.databinding.ActivityMainBinding
import br.eng.pedro_mendes.shopping_list.ui.add_shopping_list_item.AddShoppingListItemActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                activityResult.data?.let {
                    if (it.hasExtra(RESULT_TAG)) {
                        val shoppingListItem =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                it.getParcelableExtra(RESULT_TAG, ShoppingListItem::class.java)
                            } else {
                                it.getParcelableExtra<ShoppingListItem>(RESULT_TAG)
                            }
                        viewModel.add(shoppingListItem!!)
                    }
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViewModel()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        setShoppingListObserver()
    }

    private fun setShoppingListObserver() {
        viewModel.shoppingList.observe(this) {
            binding.apply {
                recycleViewShoppingList.adapter = ShoppingListAdapter(
                    it,
                    viewModel::setCheck,
                    viewModel::deleteItem,
                )
                textItemListLength.text = if (it.isEmpty()) {
                    getString(R.string.empty_list)
                } else {
                    resources.getQuantityString(
                        R.plurals.list_length,
                        it.size,
                        it.size
                    )
                }
            }
        }
    }

    private fun initializeViewModel() = viewModel.initialize()

    private fun setupListeners() {
        setupAddShoppingListButton()
    }

    private fun setupAddShoppingListButton() {
        binding.fabAddShoppingListItem.setOnClickListener {
            activityResultLauncher.launch(Intent(this, AddShoppingListItemActivity::class.java))
        }
    }

    companion object {
        const val RESULT_TAG = "ADD_SHOPPING_LIST_ITEM"
    }
}