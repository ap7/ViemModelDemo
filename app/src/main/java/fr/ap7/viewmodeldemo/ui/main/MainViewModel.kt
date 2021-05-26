package fr.ap7.viewmodeldemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val RESULT_KEY = "Euro value"
const val DOLLAR_KEY = "Dollar value"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // Define data model within the view model
    private val usdToEuRate = 0.4f

    //var dollarValue: MutableLiveData<String> = MutableLiveData()
    var dollarValue: MutableLiveData<String> = savedStateHandle.getLiveData(DOLLAR_KEY)

    // This value is wrapped to MutableLiveData instance
    // result value is mutable because the value can be  changed each time the user request it
    // var result: MutableLiveData<Float> = MutableLiveData()
    var result: MutableLiveData<Float> = savedStateHandle.getLiveData(RESULT_KEY)

    /*fun setAmount(value: String) {
        try {
            dollarText = value
            result.setValue(value.toFloat() * usdToEuRate)
        } catch (e: Exception) {
            print(e.message)
        }
    }

    fun getResult(): MutableLiveData<Float> {
        return result
    }*/

    // Adding the conversion method
    fun convertValue() {
        dollarValue.let {
            if (!it.value.equals("")) {
                result.value = it.value?.toFloat()?.times(usdToEuRate)
                val convertedValue  = result.value
                savedStateHandle.set(RESULT_KEY, convertedValue)
                savedStateHandle.set(DOLLAR_KEY, it.value)
            } else {
                result.value = 0F
            }
        }
    }
}