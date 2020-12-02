package android.example.midterm.ui.homescreen

import android.example.midterm.R
import android.example.midterm.data.repo.SeriesFetchRepository
import android.example.midterm.ui.viewmodel.HomeScreenViewModel
import android.example.midterm.util.BaseFragment
import android.example.midterm.util.SeriesSharedPreferences
import android.example.midterm.util.createViewModel
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.homescreenfragment.view.*

class HomeScreenFragment : BaseFragment<Any?, Any?>() {
    override val layoutResourceID: Int = R.layout.homescreenfragment
    val model by lazy { createViewModel { HomeScreenViewModel(SeriesApi = SeriesFetchRepository.getSeriesFetch(), seriesSharedPref = SeriesSharedPreferences.getSharedPrefImpl(requireContext())) } }
    private var recyclerView: RecyclerView? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myAdapter = homeScreenAdapter(this::navigateToSerie)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.SerieCall()
        viewManager = LinearLayoutManager(requireContext())
        model.seriesArrayLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                myAdapter.setData(it)
            }
        })
        recyclerView = view.findViewById<RecyclerView>(R.id.homescreenrecycler)?.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = myAdapter

        }
    }

    fun navigateToSerie(serieData: String?){
        val bundle = Bundle()
        bundle.apply { serieData?.let { putString("key0", it) }
        }
        findNavController().navigate(R.id.action_baseLandingPage_to_HomeScreenFragment, bundle)
    }

}