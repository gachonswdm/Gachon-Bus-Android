package io.github.gachonswdm.gachonbus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import io.github.gachonswdm.gachonbus.R
import io.github.gachonswdm.gachonbus.model.TimelineModel

class TimelineAdapter(private val mFeedList: List<TimelineModel>) : RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun getItemCount(): Int = mFeedList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeLineModel = mFeedList[position]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_timeline, parent, false), viewType)
    }

    inner class ViewHolder(itemView: View, viewType: Int): RecyclerView.ViewHolder(itemView) {
    }
}