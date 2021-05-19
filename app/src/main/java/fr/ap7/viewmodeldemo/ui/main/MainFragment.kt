package fr.ap7.viewmodeldemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import fr.ap7.viewmodeldemo.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Associating the view (fragment) with the view model
        // Fragment or Activity maintain references to the view models
        // Fragment or Activity relies on data using an instance of the view model provider class
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // This is here we needs to updated the react of views (button,..)
        // Interact whit the data stores int the view model


        // Configure an observer within the ui controller
        // This an instances, is an lambda when it called, is passed the current result value which it convert to a string
        // Add an observer to the result livedata object
        // The result printed in Textview is now under responsibility of the onChanged method from Observer interface
        // The observer received notification that the result value had changed and called the onChanged method to display the latest data
        val resultObserver = Observer<Float> { result ->  resultText.text = result.toString() }

        viewModel.getResult().observe(viewLifecycleOwner, resultObserver)

        convertButton.setOnClickListener {
            if (resultText.text.isNotEmpty()) {
                viewModel.setAmount(dollarText.text.toString())
            } else {
                resultText.text = R.string.no_value.toString()
            }
        }
    }
}