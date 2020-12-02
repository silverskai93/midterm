package android.example.midterm.ui.overviewscreen

import android.example.midterm.R
import android.example.midterm.data.model.model.EpisodeDetails
import android.example.midterm.data.repo.OverviewFetchRepository
import android.example.midterm.ui.overviewscreen.adapter.ThirdAdapter
import android.example.midterm.ui.viewmodel.OverviewScreenViewModel
import android.example.midterm.util.BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : BaseFragment<Any?, Any?>(){
    override val layoutResourceID: Int = R.layout.overviewfragment
    val viewModel: OverviewScreenViewModel? by viewModel()
    private var recyclerView: RecyclerView? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myAdapter = ThirdAdapter()
    var seasonsID : String? = null
    var stonksdata2 : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            stonksdata2 = arguments?.getString("key4")
            seasonsID = arguments?.getString("key3")

        }
        viewModel?.overviewCall(stonksdata2, seasonsID)
        viewManager = LinearLayoutManager(requireContext())
        viewModel?.overviewArrayLiveData?.observe(viewLifecycleOwner, Observer{
            if(it != null){
                myAdapter.setData(it)
            }
        })

        recyclerView = view.findViewById<RecyclerView>(R.id.overviewrecycler)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = myAdapter
        }
    }

}