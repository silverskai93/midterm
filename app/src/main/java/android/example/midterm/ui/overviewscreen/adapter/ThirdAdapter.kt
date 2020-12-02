package android.example.midterm.ui.overviewscreen.adapter

import android.example.midterm.R
import android.example.midterm.data.model.model.OverViewData
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ThirdAdapter(): RecyclerView.Adapter<ThirdAdapter.ThirdViewHolder>(){
    private var overviewArrayList : List<OverViewData> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        return ThirdViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return overviewArrayList.size ?:0
    }

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        holder.bind(overviewArrayList.get(position))
    }

    fun setData(overviewData: OverViewData){
        this.overviewArrayList = listOf(overviewData)
        notifyDataSetChanged()
    }
    inner class ThirdViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.overviewviewholder, parent, false)){
        private val episodeTitle = itemView.findViewById<TextView>(R.id.overviewTitle)
        private val episodeInfo = itemView.findViewById<TextView>(R.id.overviewinfo)
        private val episodeImage = itemView.findViewById<ImageView>(R.id.overviewimage)

        fun bind(overviewDataWrapper: OverViewData){
        episodeTitle.text = overviewDataWrapper.Title
        episodeInfo.text = overviewDataWrapper.Episode.toString()
            Glide.with(episodeImage.context)
                .load("")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(episodeImage)
        }
    }
}