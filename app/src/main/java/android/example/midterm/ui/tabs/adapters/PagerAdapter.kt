package android.example.midterm.ui.tabs.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(
    private val baseLandingPageList: List<Pair<Fragment, String>>,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = baseLandingPageList.size

    override fun getItem(position: Int): Fragment {
        return baseLandingPageList[position].first
    }

    override fun getPageTitle(position: Int): CharSequence {
        return baseLandingPageList[position].second
    }
}
