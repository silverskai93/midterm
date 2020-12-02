package android.example.midterm.ui.seasonscreen.adapter

import android.example.midterm.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var onItemClick:((Int?) -> Unit)): RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var seasonArrayList : ArrayList<Int> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return seasonArrayList.size ?:0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(seasonArrayList.get(position))
    }

    fun setData(newData: ArrayList<Int>) {
        this.seasonArrayList = newData
        notifyDataSetChanged()
    }
    inner class MyViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.seriesviewholder, parent, false)){
        private val seasonTotal = itemView.findViewById<TextView>(R.id.seasontotal)

        fun bind(seasonDataWrapper: Int?){
            seasonTotal.text = seasonDataWrapper.toString()

            itemView.setOnClickListener {
                if (seasonDataWrapper != null){
                    onItemClick.invoke(seasonDataWrapper)
                }
            }
        }
    }
}