package android.example.midterm.ui.episodescreen

import android.example.midterm.R
import android.example.midterm.data.repo.EpisodesFetchRepository
import android.example.midterm.ui.episodescreen.adapter.SecondAdapter
import android.example.midterm.ui.viewmodel.EpisodeScreenViewModel
import android.example.midterm.util.BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

class EpisodesFragment : BaseFragment<Any?, Any?>() {
    override val layoutResourceID: Int = R.layout.episodefragment
    val viewModel: EpisodeScreenViewModel? by viewModel()
    var stonksData: Int? = null
    var imbdID: String? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myAdapter = SecondAdapter(this::navigateToOverview)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            stonksData = arguments?.getInt("key")
            imbdID = arguments?.getString("key2")

        }
        viewModel?.seasonCall(stonksData.toString(), imbdID)
        viewManager = LinearLayoutManager(requireContext())
        viewModel?.episodeArrayLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                myAdapter.setData(it)
            }

        })
        recyclerView = view.findViewById<RecyclerView>(R.id.episoderecycler)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = myAdapter

        }
    }

    fun navigateToOverview(epData: String?) {
        val bundle = Bundle()
        bundle.apply {
            viewModel?.seasonID?.let { putString("key3", it) }
            epData?.let { putString("key4", it) }
        }
    }
}