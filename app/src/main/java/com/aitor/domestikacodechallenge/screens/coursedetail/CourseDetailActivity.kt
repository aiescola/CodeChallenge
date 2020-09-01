package com.aitor.domestikacodechallenge.screens.coursedetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aitor.domestikacodechallenge.R
import com.aitor.domestikacodechallenge.databinding.ActivityCourseDetailBinding
import com.aitor.domestikacodechallenge.model.CourseUI
import com.aitor.domestikacodechallenge.player.ExoPlayerFragment
import com.aitor.domestikacodechallenge.player.model.MediaItem

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCourseDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        requireNotNull(intent?.extras).getParcelable<CourseUI>(COURSE_UI_KEY)?.let {
            with(binding) {
                courseDetailInfo.populate(
                    it.title,
                    it.description,
                    it.teacher.name,
                    it.location,
                    it.teacher.avatarUrl
                )
                courseDetailStats.populate(
                    it.reviews.positivePct,
                    it.reviews.total,
                    it.lessonsCount,
                    it.students,
                    it.audio,
                    it.subtitles,
                    it.level
                )
            }

            setupPlayer(it)
        }
    }

    private fun setupPlayer(courseUI: CourseUI) {
        val mediaItem = MediaItem(courseUI.trailerUrl)

        val fragment = ExoPlayerFragment.newInstance(mediaItem)
        supportFragmentManager.beginTransaction().add(R.id.player_container, fragment, null).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.course_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_upload_button -> {
                Toast.makeText(this, "Option not available", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private const val COURSE_UI_KEY = "course_ui_key"

        fun open(activity: Activity, courseUI: CourseUI) {
            val intent = Intent(activity, CourseDetailActivity::class.java)
            intent.putExtra(COURSE_UI_KEY, courseUI)
            activity.startActivity(intent)
        }
    }
}
