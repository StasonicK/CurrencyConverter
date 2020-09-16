package com.eburg_soft.currencyconverter.features.converter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.extensions.injectViewModel
import com.eburg_soft.currencyconverter.extensions.observe
import com.eburg_soft.currencyconverter.features.converter.viewmodel.ConverterViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_converter.btnClear
import kotlinx.android.synthetic.main.fragment_converter.btnCount
import kotlinx.android.synthetic.main.fragment_converter.etFirstCurrencyNumber
import kotlinx.android.synthetic.main.fragment_converter.pbConverter
import kotlinx.android.synthetic.main.fragment_converter.spFirstCurrencyType
import kotlinx.android.synthetic.main.fragment_converter.spSecondCurrencyType
import kotlinx.android.synthetic.main.fragment_converter.tvResultSecondCurrencyNumber
import timber.log.Timber
import toothpick.Toothpick

class ConverterFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    private val viewModel: ConverterViewModel by lazy {
        injectViewModel(ConverterViewModel::class, Scopes.CONVERTER)
    }

    //region ====================== Android methods ======================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        Timber.d("onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onStart() {
        super.onStart()

        setupUI()
        setupListeners()
        observeLiveData()
        // temporary
        showHistoryAction(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_item, menu)
    }

    //endregion

    private fun setupUI() {
        toolbar = activity?.findViewById(R.id.toolbarConverterFragment)!!
        toolbar.apply {
            inflateMenu(R.menu.menu_item)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_action_history_fragment -> {
                        Navigation.findNavController(requireView())
                            .navigate(R.id.fragment_history_fragment)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupListeners() {
        btnClear.setOnClickListener {
            etFirstCurrencyNumber.setText("")
            spFirstCurrencyType.setSelection(0)
            tvResultSecondCurrencyNumber.text = ""
            spSecondCurrencyType.setSelection(0)
        }
        btnCount.setOnClickListener {
            if (etFirstCurrencyNumber.text.isNullOrEmpty()) {
                showSnackbarEmptyFirstCurrentNumber()
            } else
                viewModel.saveCurrencyConversion(
                    etFirstCurrencyNumber.text.toString(),
                    spFirstCurrencyType.selectedItem.toString(),
                    spSecondCurrencyType.selectedItem.toString()
                )
        }
    }

    private fun observeLiveData() {
        observe(viewModel.secondCurrenciesNumberLiveData) { setSecondCurrencyNumber(it) }
        observe(viewModel.isLoadingLiveData) { showLoading(it) }
        observe(viewModel.errorOnLoadingLiveData) { onErrorReceived(it) }
//        observe(viewModel.isExistingHistoryLiveData) { showHistoryAction(it) }
    }

    private fun onErrorReceived(message: String) {
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.network_connection_error_title)
            .setMessage(message)
            .setCancelable(true)
            .setNegativeButton(R.string.network_connection_error_cancel) { dialog, _ -> dialog.cancel() }
            .setPositiveButton(R.string.network_connection_error_action) { _, _ ->
                viewModel.saveCurrencyConversion(
                    etFirstCurrencyNumber.text.toString(),
                    spFirstCurrencyType.selectedItem.toString(),
                    spSecondCurrencyType.selectedItem.toString()
                )
            }
            .show()
    }

    private fun showLoading(isLoading: Boolean) {
        pbConverter.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setSecondCurrencyNumber(secondCurrencyNumber: Double) {
        tvResultSecondCurrencyNumber.text = secondCurrencyNumber.toString()
    }

    private fun showHistoryAction(isShowing: Boolean) {
        toolbar.menu?.let {
            it.findItem(R.id.menu_action_history_fragment).apply {
                isVisible = isShowing
                isEnabled = isShowing
            }
        }
        Timber.d("showHistoryAction()")
    }

    private fun showSnackbarEmptyFirstCurrentNumber() {
        Snackbar.make(requireView(), R.string.first_currency_number_is_empty, Snackbar.LENGTH_LONG).show()
    }
}