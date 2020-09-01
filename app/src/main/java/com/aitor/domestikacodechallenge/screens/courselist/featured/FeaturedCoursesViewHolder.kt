package com.aitor.domestikacodechallenge.screens.courselist.featured

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.aitor.domestikacodechallenge.R
import com.aitor.domestikacodechallenge.common.OnWatchCourseClickListener
import com.aitor.domestikacodechallenge.databinding.ItemFeaturedCourseBinding
import com.aitor.domestikacodechallenge.model.CourseUI

class FeaturedCoursesViewHolder(private val binding: ItemFeaturedCourseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(course: CourseUI, onWatchClickListener: OnWatchCourseClickListener) {
        with(binding) {
            titleText.text = course.title
            featuredImage.load(course.thumbnailUrl) {
                crossfade(root.context.resources.getInteger(R.integer.crossfade_duration))
            }
            watchButton.setOnClickListener {
                onWatchClickListener(course)
            }
        }
    }
}
