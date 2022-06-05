package com.yseko.currencyconversion


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yseko.currencyconversion.network.FixerApi
import com.yseko.currencyconversion.network.ResponseConvert
import com.yseko.currencyconversion.network.ResponseSymbols
import kotlinx.coroutines.launch

class ConverterViewModel: ViewModel() {
    private val apikey = ""

    private var _responseConvert = MutableLiveData<ResponseConvert>()
    val responseConvert: LiveData<ResponseConvert> = _responseConvert

    private var _responseSymbols = MutableLiveData<ResponseSymbols>()
    val responseSymbols: LiveData<ResponseSymbols> = _responseSymbols

    private var _input = MutableLiveData<String>("")
    val input: LiveData<String> = _input

    private var _output = MutableLiveData<String>("")
    val output: LiveData<String> = _output

    private var _options = MutableLiveData<List<String>>(emptyList())
    val options: LiveData<List<String>> = _options

    private var _symbols = MutableLiveData<List<String>>(emptyList())
    val symbols: LiveData<List<String>> = _symbols

    lateinit var toConvert: String
    lateinit var fromConvert: String

    fun onClickNum(num: Int){
        _input.value = input.value + num.toString()
    }

    fun onClickDelete(){
        _input.value = ""
    }

    fun onClickDec(){
        _input.value = input.value+"."
    }

    fun getConvert(fromConvert: String, toConvert: String, amount: String){
        viewModelScope.launch {
            try{
                _responseConvert.value = FixerApi.retrofitService.getConvert(amount, fromConvert, toConvert, apikey)
                _output.value = responseConvert.value?.result.toString()
            }catch(e: Exception){
                _output.value = "Failure: ${e.message}"
                println(e.message)
            }
        }
    }

    fun getSymbols(){
        viewModelScope.launch{
            try{
                _responseSymbols.value = FixerApi.retrofitService.getSymbols(apikey)
//                _options.value = responseSymbols.value.symbols.values.toList()
                _options.value = responseSymbols.value?.symbols?.values?.toList()!!
                _symbols.value = responseSymbols.value?.symbols?.keys?.toList()!!
            }catch(e: Exception){
                _output.value = "Failure: ${e.message}"
                println(e.message)
            }
        }
    }


}