package com.eburg_soft.currencyconverter.features.converter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.extensions.injectViewModel
import com.eburg_soft.currencyconverter.extensions.observe
import com.eburg_soft.currencyconverter.features.converter.viewmodel.ConverterViewModel
import kotlinx.android.synthetic.main.fragment_converter.btnClear
import kotlinx.android.synthetic.main.fragment_converter.btnCount
import kotlinx.android.synthetic.main.fragment_converter.etFirstCurrencyNumber
import kotlinx.android.synthetic.main.fragment_converter.pbConverter
import kotlinx.android.synthetic.main.fragment_converter.spFirstCurrencyType
import kotlinx.android.synthetic.main.fragment_converter.spSecondCurrencyType
import kotlinx.android.synthetic.main.fragment_converter.tvResultSecondCurrencyNumber
import timber.log.Timber

class ConverterFragment : Fragment() {

    private var menu: Menu? = null
//    private lateinit var navController: NavController

    companion object {

        private const val SECOND_CURRENCY_TYPE = "SECOND_CURRENCY_TYPE"
        private const val SECOND_CURRENCY_NUMBER = "SECOND_CURRENCY_NUMBER"
        private const val FIRST_CURRENCY_TYPE = "FIRST_CURRENCY_TYPE"
        private const val FIRST_CURRENCY_NUMBER = "FIRST_CURRENCY_NUMBER"
    }

    private val viewModel: ConverterViewModel by lazy {
        injectViewModel(ConverterViewModel::class, Scopes.CONVERTER)
    }

    //region ====================== Android methods ======================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        //  recover data
        if (savedInstanceState != null) {
            etFirstCurrencyNumber.setText(savedInstanceState.getString(FIRST_CURRENCY_NUMBER))
            tvResultSecondCurrencyNumber.text = savedInstanceState.getString(SECOND_CURRENCY_NUMBER)
            spFirstCurrencyType.setSelection(savedInstanceState.getString(FIRST_CURRENCY_TYPE)!!.toInt())
            spSecondCurrencyType.setSelection(savedInstanceState.getString(SECOND_CURRENCY_TYPE)!!.toInt())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Timber.d("onActivityCreated()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNevController(view)
        setupListeners()
        observeLiveData()

        Timber.d("onViewCreated()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FIRST_CURRENCY_NUMBER, etFirstCurrencyNumber.text.toString())
        outState.putString(SECOND_CURRENCY_NUMBER, tvResultSecondCurrencyNumber.text.toString())
        outState.putString(FIRST_CURRENCY_TYPE, spFirstCurrencyType.selectedItemPosition.toString())
        outState.putString(SECOND_CURRENCY_TYPE, spSecondCurrencyType.selectedItemPosition.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_history_fragment -> {
//                navController.navigate(R.id.open_history_fragment)
//                Navigation.findNavController(requireView()).navigate(R.id.fragment_history_fragment)

//                Navigation.createNavigateOnClickListener(R.id.fragment_history_fragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_item, menu)
    }

    //endregion

    private fun setNevController(view: View) {
//        navController = Navigation.findNavController(view)
    }

    private fun setupListeners() {
        btnClear.setOnClickListener {
            etFirstCurrencyNumber.setText(R.string.default_currency_number)
            spFirstCurrencyType.setSelection(0)
            tvResultSecondCurrencyNumber.setText(R.string.default_currency_number)
            spSecondCurrencyType.setSelection(0)
        }
        btnCount.setOnClickListener {
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
        observe(viewModel.isErrorOnLoadingLiveData) { onErrorReceived() }
        observe(viewModel.isExistingHistorySizeLiveData) { showHistoryAction(it) }
    }

    private fun onErrorReceived() {
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.network_connection_error_title)
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
        menu?.let {
            it.findItem(R.id.menu_action_history_fragment).apply {
                isVisible = isShowing
                isEnabled = isShowing
            }
        }
    }
}