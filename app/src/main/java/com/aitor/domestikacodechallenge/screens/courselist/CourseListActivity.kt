package com.aitor.domestikacodechallenge.screens.courselist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.aitor.domestikacodechallenge.common.ErrorTypeUI
import com.aitor.domestikacodechallenge.common.ScreenStatus
import com.aitor.domestikacodechallenge.common.setVisible
import com.aitor.domestikacodechallenge.databinding.ActivityCourseListBinding
import com.aitor.domestikacodechallenge.model.CourseUI
import com.aitor.domestikacodechallenge.screens.coursedetail.CourseDetailActivity
import com.aitor.domestikacodechallenge.screens.courselist.featured.FeaturedCoursesPagerAdapter
import com.aitor.domestikacodechallenge.screens.courselist.popular.PopularCoursesAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class CourseListActivity : AppCompatActivity() {

    private val viewModel: CourseListViewModel by viewModel()
    private lateinit var binding: ActivityCourseListBinding

    private val featuredAdapter: FeaturedCoursesPagerAdapter by lazy {
        FeaturedCoursesPagerAdapter {
            navigateToCourseDetail(it)
        }
    }
    private val popularCoursesAdapter: PopularCoursesAdapter by lazy {
        PopularCoursesAdapter {
            navigateToCourseDetail(it)
        }
    }

    private val screenStatusObserver = Observer<ScreenStatus> {
        when (it) {
            is ScreenStatus.Loading -> {
                setupLoadingScreen()
            }
            is ScreenStatus.Success -> {
                setupSuccessScreen()
            }
            is ScreenStatus.Error -> {
                setupErrorScreen(it.errorType)
            }
        }
    }

    private val featuredCoursesObserver = Observer<List<CourseUI>> {
        featuredAdapter.submitList(it)
    }

    private val popularCoursesObserver = Observer<List<CourseUI>> {
        popularCoursesAdapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCourseListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupFeaturedCoursesView()
        setupPopularCoursesView()

        binding.searchButton.setOnClickListener {
            Toast.makeText(this, "Option not available", Toast.LENGTH_SHORT).show()
        }

        viewModel.screenStatus.observe(this, screenStatusObserver)

        viewModel.featuredCoursesLiveData.observe(this, featuredCoursesObserver)

        viewModel.popularCoursesLiveData.observe(this, popularCoursesObserver)
    }

    private fun navigateToCourseDetail(courseUI: CourseUI) {
        CourseDetailActivity.open(this, courseUI)
    }

    private fun setupFeaturedCoursesView() {
        binding.featuredCoursesPager.apply {
            adapter = featuredAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            TabLayoutMediator(binding.pagerIndicator, this) { _, _ -> }
                .attach()
        }
    }

    private fun setupPopularCoursesView() {
        binding.popularRecyclerview.apply {
            adapter = popularCoursesAdapter
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupLoadingScreen() {
        with(binding) {
            listOf(popularRecyclerview, popularLabel, searchButton, layoutError.errorRefresh, layoutError.errorText)
                .setVisible(false)

            loading.visibility = View.VISIBLE
        }
    }

    private fun setupSuccessScreen() {
        with(binding) {
            listOf(loading, layoutError.errorRefresh, layoutError.errorText).setVisible(false)

            listOf(popularRecyclerview, popularLabel, searchButton).setVisible()
        }
    }

    private fun setupErrorScreen(error: ErrorTypeUI) {
        with(binding) {
            listOf(popularRecyclerview, popularLabel, loading, searchButton).setVisible(false)

            with(layoutError) {
                errorText.text = getString(error.resId)
                errorRefresh.setOnClickListener {
                    viewModel.loadCourses()
                }

                listOf(errorText, errorRefresh).setVisible()
            }
        }
    }
}
