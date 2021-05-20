package fr.ap7.viewmodeldemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Define data model within the view model
    private val usdToEuRate = 0.4f

    var dollarValue: MutableLiveData<String> = MutableLiveData()

    // This value is wrapped to MutableLiveData instance
    // result value is mutable because the value can be  changed each time the user request it
    var result: MutableLiveData<Float> = MutableLiveData()

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
            } else {
                result.value = 0F
            }
        }
    }
}