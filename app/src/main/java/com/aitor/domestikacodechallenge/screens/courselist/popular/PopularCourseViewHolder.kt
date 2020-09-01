package com.aitor.domestikacodechallenge.screens.courselist.popular

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.aitor.domestikacodechallenge.R
import com.aitor.domestikacodechallenge.common.OnWatchCourseClickListener
import com.aitor.domestikacodechallenge.databinding.ItemCourseCardBinding
import com.aitor.domestikacodechallenge.model.CourseUI

class PopularCourseViewHolder(private val bind: ItemCourseCardBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(courseUI: CourseUI, onWatchCourseClickListener: OnWatchCourseClickListener) {
        with(bind) {
            title.text = courseUI.title
            teacherName.text = courseUI.teacher.name
            courseImage.load(courseUI.thumbnailUrl) {
                crossfade(root.context.resources.getInteger(R.integer.crossfade_duration))
            }
            root.setOnClickListener {
                onWatchCourseClickListener(courseUI)
            }
        }
    }
}
