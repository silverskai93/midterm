package android.example.midterm.ui.tabs.searchtab

import android.example.midterm.R
import android.example.midterm.ui.tabs.adapters.PagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class SearchTabFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.searchtabfrag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}

