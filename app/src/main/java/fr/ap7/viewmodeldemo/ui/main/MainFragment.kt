package fr.ap7.viewmodeldemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        resultText.text = viewModel.getResult().toString()

        convertButton.setOnClickListener {
            if (resultText.text.isNotEmpty()) {
                viewModel.setAmount(dollarText.text.toString())
                resultText.text = viewModel.getResult().toString()
            } else {
                resultText.text = R.string.no_value.toString()
            }
        }
    }
}