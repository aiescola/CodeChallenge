package com.aitor.domestikacodechallenge.screens.courselist.featured

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aitor.domestikacodechallenge.common.OnWatchCourseClickListener
import com.aitor.domestikacodechallenge.databinding.ItemFeaturedCourseBinding
import com.aitor.domestikacodechallenge.model.CourseUI

class FeaturedCoursesPagerAdapter(private val onWatchClickListener: OnWatchCourseClickListener) :
    ListAdapter<CourseUI, FeaturedCoursesViewHolder>(
        DIFF_UTIL
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedCoursesViewHolder {
        val binding =
            ItemFeaturedCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FeaturedCoursesViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: FeaturedCoursesViewHolder, position: Int) {
        holder.bind(getItem(position), onWatchClickListener)
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
