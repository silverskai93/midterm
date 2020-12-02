package android.example.midterm.ui.seasonscreen

import android.example.midterm.R
import android.example.midterm.data.repo.SeasonsFetchRepository
import android.example.midterm.ui.seasonscreen.adapter.Adapter
import android.example.midterm.ui.viewmodel.SeasonScreenViewModel
import android.example.midterm.util.BaseFragment
import android.example.midterm.util.createViewModel
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class SeasonScreenFragment : BaseFragment<Any?, Any?>() {
    override val layoutResourceID: Int = R.layout.seasonscreenfragment
    val viewModel: SeasonScreenViewModel? by viewModel()

    private var recyclerView: RecyclerView? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myAdapter = Adapter(this::chosenSeason)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.seriesCall()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(requireContext())
        viewModel?.seasonArrayLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                myAdapter.setData(it)
            }
        })

        recyclerView = view.findViewById<RecyclerView>(R.id.seasonrecycler)?.apply {
            setHasFixedSize(true)
            adapter = myAdapter
            layoutManager = viewManager

        }
    }

    fun chosenSeason(spData: Int?) {
        val bundle = Bundle()
        bundle.apply {
            viewModel?.idData?.let { putString("key2", it) }
            spData?.let { putInt("key", it) }
        }
        findNavController().navigate(R.id.action_seasonscreenfragment_to_episodefragment, bundle)
    }

}