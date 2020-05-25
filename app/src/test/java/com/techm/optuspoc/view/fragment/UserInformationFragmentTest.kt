package com.techm.optuspoc.view.fragment

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.network.ApiInterface
import com.techm.optuspoc.repository.RepositoryViewModel
import com.techm.optuspoc.viewModel.ViewModelUserInformation
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@RunWith(JUnit4::class)
class UserInformationFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var mApiInterface: ApiInterface

    lateinit var mViewModelUserInformation: ViewModelUserInformation

    lateinit var mRepositoryViewModel: RepositoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mRepositoryViewModel = RepositoryViewModel()
        val application = Mockito.mock(Application::class.java)
        this.mViewModelUserInformation = ViewModelUserInformation(application)
    }
   /* @Test
    fun test_getEmployeeInformationAPISuccess() {
        `when`(this.mApiInterface.getUserList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<com.techm.optuspoc.repository.RepositoryViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<ModelServerResponse>
        this.mViewModelEmployeeInformation.mEmployeeInformationData.observeForever(observer)

        this.mViewModelEmployeeInformation.getEmployeeInformation()

        assertNotNull(this.mViewModelEmployeeInformation.mEmployeeInformationData.value)
    }
*/

}


