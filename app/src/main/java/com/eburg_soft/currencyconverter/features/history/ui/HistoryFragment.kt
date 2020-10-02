package com.eburg_soft.currencyconverter.features.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.eburg_soft.currencyconverter.R
import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.di.Scopes
import com.eburg_soft.currencyconverter.extensions.injectViewModel
import com.eburg_soft.currencyconverter.extensions.observe
import com.eburg_soft.currencyconverter.features.history.ui.adapter.CurrencyConversionAdapter
import com.eburg_soft.currencyconverter.features.history.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.fab
import kotlinx.android.synthetic.main.fragment_history.pbHistory
import kotlinx.android.synthetic.main.fragment_history.recycler_view_currency_conversions
import timber.log.Timber

class HistoryFragment : Fragment() {

    private var savedInstanceState: Bundle? = null
    private val currencyConversionAdapter = CurrencyConversionAdapter()
    private lateinit var toolbar: Toolbar

    private val viewModel: HistoryViewModel by lazy {
        injectViewModel(HistoryViewModel::class, Scopes.HISTORY)
    }

    companion object {

        private const val KEY_LAST_ITEM_POSITION = "KEY_LAST_ITEM_POSITION"
    }

    //region ====================== Android methods ======================

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.savedInstanceState = savedInstanceState

        observerLiveData()
        setupUI()
        // handle back button
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Timber.d("Activity back pressed invoked")
                    // if you want onBackPressed() to be called as normal afterwards
                    if (isEnabled) {
                        isEnabled = false
                        view?.let { Navigation.findNavController(it).navigateUp() }
                    }
                }
            }
        )
        Timber.d("onActivityCreated()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveRecyclerViewState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // handle navigateUp
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //endregion

    private fun setupUI() {
        fab.setOnClickListener {
            viewModel.removeAllHistory()
            Navigation.findNavController(requireView()).navigate(R.id.fragment_converter)
        }
        toolbar = view?.findViewById(R.id.toolbarHistoryFragment)!!
        toolbar.apply {
            inflateMenu(R.menu.menu_item)
            menu.removeItem(R.id.menu_action_history_fragment)
            setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
            setNavigationOnClickListener {
                Navigation.findNavController(requireView()).navigateUp()
            }
        }
    }

    private fun observerLiveData() {
        observe(viewModel.currencyConversionListLiveData) { showCurrencyConversions(it) }
        observe(viewModel.isLoadingLiveData) { showLoading(it) }
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
            Timber.d("$KEY_LAST_ITEM_POSITION is $lastItemPosition")
        }
    }

    private fun populateRecyclerView(currencyConversions: List<CurrencyConversionEntity>) {
        recycler_view_currency_conversions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            currencyConversionAdapter.setData(currencyConversions)
            adapter = currencyConversionAdapter
            setHasFixedSize(true)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, swipeDir: Int) {
                Toast.makeText(requireActivity(), R.string.currency_conversion_deleted, Toast.LENGTH_SHORT).show()
                val position = viewHolder.adapterPosition
                viewModel.remove(currencyConversionAdapter.getCurrencyConversionAt(position))
                currencyConversionAdapter.removeCurrencyConversion(position)
            }
        }).attachToRecyclerView(recycler_view_currency_conversions)
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
}