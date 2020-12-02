package android.example.midterm.ui.homescreen

import android.example.midterm.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class homeScreenAdapter(private var onItemClick:((String?) -> Unit)): RecyclerView.Adapter<homeScreenAdapter.HomeViewHolder>(){
    private var seriesArrayList : ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return seriesArrayList.size ?:0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(seriesArrayList.get(position))
    }
    fun setData(serieData: ArrayList<String>) {
        this.seriesArrayList = serieData
        notifyDataSetChanged()
    }
    inner class HomeViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.homescreenviewholder, parent, false)){
        private val seriesTitle = itemView.findViewById<TextView>(R.id.seriestitle)

        fun bind(seriesDataWrapper: String?){
            seriesTitle.text = seriesDataWrapper

            itemView.setOnClickListener {
                if (seriesDataWrapper != null){
                    onItemClick.invoke(seriesDataWrapper)
                }
            }

        }
    }
}