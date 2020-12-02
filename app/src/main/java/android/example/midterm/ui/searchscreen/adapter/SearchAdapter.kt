package android.example.midterm.ui.searchscreen.adapter

import android.example.midterm.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(private var onItemClick: ((String?) -> Unit)) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchArrayList: List<String?> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return searchArrayList.size ?: 0
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.bind(searchArrayList.get(position))
    }

    fun setData(searchData: String) {
        this.searchArrayList = listOf(searchData)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.search_viewholder, parent, false)) {
        private val Title = itemView.findViewById<TextView>(R.id.temptext)

        fun bind(searchDataWrapper: String?) {
            Title.text = searchDataWrapper


            itemView.setOnClickListener {
                if (searchDataWrapper != null) {
                    onItemClick.invoke(searchDataWrapper.toString())
                }
            }
        }
    }
}