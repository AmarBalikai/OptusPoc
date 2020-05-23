package com.techm.optuspoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.optuspoc.callback.ResponseCallback
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.repository.RepositoryViewModel
import org.jetbrains.annotations.NotNull

class ViewModelUserInformation(@NotNull application: Application) : AndroidViewModel(application),
    ResponseCallback {

    private val repositoryViewModel: RepositoryViewModel by lazy { RepositoryViewModel() }
    val mUserInformationList: MutableLiveData<ArrayList<ModelUserInformation>> by lazy { MutableLiveData<ArrayList<ModelUserInformation>>() }


    init {
        repositoryViewModel.retrieveUserInformationData(this)
    }

    /**
     * Success response callback from Repository
     * @param data for get updated list from API
     */
    override fun onSuccess(data: ArrayList<ModelUserInformation>) {
        data.let { mUserInformationList.value = it }

    }

    /**
     * Failure response callback from Repository
     * @param error for get error message
     */
    override fun onError(error: String?) {
        //apiFailResponse.value = ApiFailModel(false)
    }
}