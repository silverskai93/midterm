package android.example.midterm.ui.tabs.pager

import android.example.midterm.R
import android.example.midterm.ui.homescreen.HomeScreenFragment
import android.example.midterm.ui.searchscreen.SearchFragment
import android.example.midterm.ui.seasonscreen.SeasonScreenFragment
import android.example.midterm.ui.tabs.adapters.PagerAdapter
import android.example.midterm.ui.tabs.favoritestab.FavoritesTabFragment
import android.example.midterm.ui.tabs.searchtab.SearchTabFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

class BaseLandingPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_landing_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<ViewPager>(R.id.pager).apply {
            adapter = PagerAdapter(
                listOf(
                    Pair(HomeScreenFragment(), getString(R.string.base_landing_page_home_tab)),
                    Pair(FavoritesTabFragment(), getString(R.string.base_landing_page_favorites_tab)),
                    Pair(SearchFragment(), getString(R.string.base_landing_page_search_tab))
                ), childFragmentManager
            )
        }
    }
}