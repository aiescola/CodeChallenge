package com.aitor.domestikacodechallenge.screens.coursedetail.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import coil.transform.CircleCropTransformation
import com.aitor.domestikacodechallenge.R
import com.aitor.domestikacodechallenge.databinding.CourseDetailInfoBinding

class CourseDetailInfoView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding: CourseDetailInfoBinding = CourseDetailInfoBinding.inflate(LayoutInflater.from(context), this, true)

    fun populate(title: String, description: String, author: String, location: String, avatarUrl: String) {
        binding.title.text = title
        binding.description.text = description
        binding.author.text = author
        binding.location.text = location
        binding.authorThumbnail.load(avatarUrl) {
            crossfade(binding.root.context.resources.getInteger(R.integer.crossfade_duration))
            transformations(CircleCropTransformation())
        }
    }
}
