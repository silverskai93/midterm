package android.example.midterm.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<T, U> : Fragment() {
    /*
    *  Resource ID of resource layout to be inflated
    * */
    protected abstract val layoutResourceID: Int

    /*
    * Overriding onCreateView to inflate resource layout file
    * */
    open override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceID, container, false)
    }

}