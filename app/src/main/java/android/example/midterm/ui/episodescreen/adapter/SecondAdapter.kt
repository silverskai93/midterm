package android.example.midterm.ui.episodescreen.adapter

import android.example.midterm.R
import android.example.midterm.data.model.model.EpisodeDetails
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SecondAdapter(private var onItemClick:((String?) -> Unit)): RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {
    private var episodeArrayList: List<EpisodeDetails?>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        return SecondViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return episodeArrayList?.size ?:0
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        holder.bind(episodeArrayList?.get(position))
    }

    fun setData(episodeData: List<EpisodeDetails?>?){
        this.episodeArrayList = episodeData
        notifyDataSetChanged()
    }
    inner class SecondViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.episodeviewholder, parent, false)){
        private val totalSeasons = itemView.findViewById<TextView>(R.id.totalSeasons)

        fun bind(episodeDataWrapper: EpisodeDetails?){
            totalSeasons.text = episodeDataWrapper.toString()

            itemView.setOnClickListener {
                if (episodeDataWrapper != null){
                    onItemClick.invoke(episodeDataWrapper.Episode)
                }
            }
        }
    }
}