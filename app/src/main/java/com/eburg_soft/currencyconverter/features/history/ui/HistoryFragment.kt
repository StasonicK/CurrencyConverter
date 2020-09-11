package com.eburg_soft.currencyconverter.features.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.extensions.injectViewModel
import com.eburg_soft.currencyconverter.extensions.observe
import com.eburg_soft.currencyconverter.features.MainActivity
import com.eburg_soft.currencyconverter.features.history.ui.adapter.CurrencyConversionAdapter
import com.eburg_soft.currencyconverter.features.history.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.pbHistory
import kotlinx.android.synthetic.main.fragment_history.recycler_view_currency_conversions
import timber.log.Timber

class HistoryFragment : Fragment() {

    private var savedInstanceState: Bundle? = null
    private lateinit var currencyConversionAdapter: CurrencyConversionAdapter

    private val viewModel: HistoryViewModel by lazy {
        injectViewModel(HistoryViewModel::class, Scopes.HISTORY)
    }

    companion object {

        private const val KEY_LAST_ITEM_POSITION = "KEY_LAST_ITEM_POSITION"
    }

    //region ====================== Android methods ======================

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerLiveData()

        Timber.d("%s is onViewCreated()", this::class.java.simpleName)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel = injectViewModel(HistoryViewModel::class, Scopes.HISTORY)

        Timber.d("s% is onActivityCreated()", this::class.java.simpleName)
    }

    override fun onStart() {
        super.onStart()
        setupToolbar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        saveRecyclerViewState(outState)
    }

    //endregion

    private fun observerLiveData() {
        observe(viewModel.currencyConversionListLiveData) { showCurrencyConversions(it) }
//        viewModel.currencyConversionListLiveData.observe(requireActivity(), Observer(::showCurrencyConversions))
        observe(viewModel.isLoadingLiveData) { showLoading(it) }
//        viewModel.isLoadingLiveData.observe(requireActivity(), Observer(::showLoading))
    }

    private fun showCurrencyConversions(currencyConversions: List<CurrencyConversionEntity>) {
        populateRecyclerView(currencyConversions)
        restorePreviousUIState()
        animateRecyclerViewOnlyInTheBeginning()
    }

    private fun restorePreviousUIState() {
        savedInstanceState?.let {
            val lastItemPosition = it.getInt(KEY_LAST_ITEM_POSITION)
            recycler_view_currency_conversions.scrollToPosition(lastItemPosition)
        }
    }

    private fun populateRecyclerView(currencyConversions: List<CurrencyConversionEntity>) {
        recycler_view_currency_conversions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            currencyConversionAdapter.setData(currencyConversions)
            adapter = currencyConversionAdapter.apply {
                updateAdapter(currencyConversions)
            }
            setHasFixedSize(true)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        pbHistory.apply {
            visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun animateRecyclerViewOnlyInTheBeginning() {
        if (getLastItemPosition() == 0) {
            recycler_view_currency_conversions.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_fall_down)
        }
    }

    private fun getLastItemPosition(): Int {
        var lastItemPosition = 0

        savedInstanceState?.let {
            if (it.containsKey(KEY_LAST_ITEM_POSITION)) {
                lastItemPosition = it.getInt(KEY_LAST_ITEM_POSITION)
            }
        }
        return lastItemPosition
    }

    private fun saveRecyclerViewState(outState: Bundle) {
        recycler_view_currency_conversions.apply {
            layoutManager?.let { it ->
                val lastVisibleItemPosition = (it as LinearLayoutManager).findLastVisibleItemPosition()

                outState.putInt(KEY_LAST_ITEM_POSITION, lastVisibleItemPosition)
            }
        }
    }

    private fun setupToolbar() {
        val actionBar: ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.title = ""
    }

    // TODO: 10.09.2020 create method removeCurrencyConverionBySwipe

    // TODO: 10.09.2020 create method removeCurrencyConverionsClicked() via floating button
}