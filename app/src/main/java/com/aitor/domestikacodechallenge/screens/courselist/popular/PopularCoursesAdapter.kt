package com.aitor.domestikacodechallenge.screens.courselist.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aitor.domestikacodechallenge.common.OnWatchCourseClickListener
import com.aitor.domestikacodechallenge.databinding.ItemCourseCardBinding
import com.aitor.domestikacodechallenge.model.CourseUI

class PopularCoursesAdapter(private val onWatchCourseClickListener: OnWatchCourseClickListener) : ListAdapter<CourseUI, PopularCourseViewHolder>(
    DIFF_UTIL
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCourseViewHolder {
        val binding = ItemCourseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularCourseViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: PopularCourseViewHolder, position: Int) {
        holder.bind(getItem(position), onWatchCourseClickListener)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<CourseUI>() {
            override fun areItemsTheSame(oldItem: CourseUI, newItem: CourseUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CourseUI, newItem: CourseUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}
