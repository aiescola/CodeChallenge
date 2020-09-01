package com.aitor.domestikacodechallenge.screens.courselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aitor.domestikacodechallenge.common.ErrorTypeUI
import com.aitor.domestikacodechallenge.common.ScreenStatus
import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.common.mapSuccess
import com.aitor.domestikacodechallenge.domain.usecases.FetchCoursesUseCase
import com.aitor.domestikacodechallenge.model.CourseUI
import com.aitor.domestikacodechallenge.model.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CourseListViewModel(private val fetchCoursesUseCase: FetchCoursesUseCase) : ViewModel() {

    private var _screenStatus = MutableLiveData<ScreenStatus>()
    private var _featuredCoursesLiveData = MutableLiveData<List<CourseUI>>()
    private var _popularCoursesLiveData = MutableLiveData<List<CourseUI>>()

    val popularCoursesLiveData: LiveData<List<CourseUI>>
        get() = _popularCoursesLiveData

    val featuredCoursesLiveData: LiveData<List<CourseUI>>
        get() = _featuredCoursesLiveData

    val screenStatus: LiveData<ScreenStatus>
        get() = _screenStatus

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch(Dispatchers.Default) {
            fetchCoursesUseCase().map { it.mapSuccess { courses -> courses.toUI() } }
                .collect {
                    when (it) {
                        is Resource.Loading -> onLoading()
                        is Resource.Success -> onSuccess(it)
                        is Resource.Failure -> onFailure()
                    }
                }
        }
    }

    private fun onLoading() {
        _screenStatus.postValue(ScreenStatus.Loading)
    }

    private fun onSuccess(data: Resource.Success<List<CourseUI>>) {
        _featuredCoursesLiveData
            .postValue(data.data.take(FEATURED_BANNER_PAGES))
        _popularCoursesLiveData
            .postValue(data.data.takeLast(data.data.size - FEATURED_BANNER_PAGES))

        _screenStatus.postValue(ScreenStatus.Success)
    }

    private fun onFailure() {
        // I'm not handling a complex error logic here but it could be done mapping the
        // RepositoryError embeded in the Resource.Failure
        _screenStatus.postValue(
            ScreenStatus.Error(
                ErrorTypeUI.GENERIC
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    companion object {
        private const val FEATURED_BANNER_PAGES = 4
    }
}
