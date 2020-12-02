package android.example.midterm.ui.searchscreen

import android.example.midterm.R
import android.example.midterm.data.repo.SearchFetchRepository
import android.example.midterm.databinding.SearchFragmentBinding
import android.example.midterm.ui.searchscreen.adapter.SearchAdapter
import android.example.midterm.ui.viewmodel.SearchFragmentViewModel
import android.example.midterm.util.BaseFragment
import android.example.midterm.util.SeriesSharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController


class SearchFragment :BaseFragment<SearchFragmentBinding, SearchFragmentViewModel>() {

    override val layoutResourceID: Int = R.layout.search_fragment
    protected lateinit var dataBinding: SearchFragmentBinding
    val model by lazy { SearchFragmentViewModel(SearchApi = SearchFetchRepository.getSearchFetch(), seriesSharedPref = SeriesSharedPreferences.getSharedPrefImpl(requireContext())) }
    private var recyclerView: RecyclerView? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myAdapter = SearchAdapter(this::navigateToSeries)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutResourceID, container, false)
        dataBinding.viewmodel = model
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.temptext)
        viewManager = LinearLayoutManager(requireContext())
        model.searchResultsLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                myAdapter.setData(it)
            }
        })

        recyclerView = view.findViewById<RecyclerView>(R.id.SearchRecycler)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = myAdapter
        }
    }

    fun navigateToSeries(searchData: String?) {
        val bundle = Bundle()
        bundle.apply {
            searchData.let { putString("key", it) }
        }
        findNavController().navigate(R.id.action_searchfragment_to_overviewfragment)
    }

}
