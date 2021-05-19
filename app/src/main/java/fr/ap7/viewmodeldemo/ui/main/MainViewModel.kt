package fr.ap7.viewmodeldemo.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Define data model within the view model
    private val usdToEuRate = 0.4f
    private var dollarText = ""
    private var result = 0f

    fun setAmount(value: String) {
        try {
            dollarText = value
            result = value.toFloat() * usdToEuRate
        } catch (e: Exception) {
            print(e.message)
        }
    }

    fun getResult(): Float {
        return result
    }
}