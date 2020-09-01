package com.aitor.domestikacodechallenge.screens.coursedetail.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.aitor.domestikacodechallenge.R
import com.aitor.domestikacodechallenge.databinding.CourseDetailStatsBinding
import com.aitor.domestikacodechallenge.model.SkillLevelUI

class CourseDetailStatsView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding: CourseDetailStatsBinding =
        CourseDetailStatsBinding.inflate(LayoutInflater.from(context), this, true)

    fun populate(
        positivePct: String,
        totalReviews: Int,
        lessonsCount: Int,
        studentsCount: Int,
        audioLanguages: String,
        subtitleLanguages: List<String>,
        skillLevel: SkillLevelUI
    ) {
        with(binding) {
            positiveReviews.text =
                root.context.getString(R.string.positive_reviews, positivePct, totalReviews)
            lessonsNumber.text =
                root.context.getString(R.string.lesson_count, lessonsCount)
            studentNumber.text = root.context.getString(R.string.students_count, studentsCount)
            audio.text = root.context.getString(R.string.audio, audioLanguages)

            subtitles.text = if (subtitleLanguages.isNullOrEmpty()) {
                root.context.getString(R.string.no_subtitles)
            } else {
                subtitleLanguages.joinToString(" / ")
            }

            levelValue.text = skillLevel.level
            levelValue.backgroundTintList =
                ColorStateList.valueOf(root.context.getColor(skillLevel.bgColor))
        }
    }
}
