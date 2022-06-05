package com.yseko.currencyconversion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yseko.currencyconversion.databinding.FragmentConversionBinding
import com.yseko.currencyconversion.databinding.FragmentConverterToolBinding


class ConversionFragment : Fragment() {
    private var _binding: FragmentConversionBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversion, container, false)
        return binding.root
    }


}