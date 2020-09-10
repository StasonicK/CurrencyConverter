package com.eburg_soft.currencyconverter.features.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.eburg_soft.currencyconverter.R
import kotlinx.android.synthetic.main.fragment_converter.btnOpenHistoryList

class ConverterFragment : Fragment() {

    companion object {

        fun newInstanse() = ConverterFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        btnOpenHistoryList.setOnClickListener {
            it.findNavController().navigate(R.id.open_history)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }
}