package com.aitor.domestikacodechallenge.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.aitor.domestikacodechallenge.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillLevelUI(val level: String, @ColorRes val bgColor: Int) : Parcelable {
    companion object {
        fun fromString(str: String): SkillLevelUI = when (str) {
            "Beginner" -> SkillLevelUI(str, R.color.skill_beginner)
            "Intermediate" -> SkillLevelUI(str, R.color.skill_intermediate)
            "Advanced" -> SkillLevelUI(str, R.color.skill_advanced)
            else -> SkillLevelUI(str, R.color.skill_default)
        }
    }
}
