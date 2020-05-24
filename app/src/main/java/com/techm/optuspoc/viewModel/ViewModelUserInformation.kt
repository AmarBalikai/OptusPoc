package com.techm.optuspoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.optuspoc.callback.ResponseCallback
import com.techm.optuspoc.model.ModelResponseHandle
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.repository.RepositoryViewModel
import com.techm.optuspoc.utils.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelUserInformation(@NotNull application: Application) : AndroidViewModel(application),
    ResponseCallback<Any> {
    var mModelResponseHandle = MutableLiveData<ModelResponseHandle>()
    private val repositoryViewModel: RepositoryViewModel by lazy { RepositoryViewModel() }
    val mUserInformationList: MutableLiveData<ArrayList<ModelUserInformation>> by lazy { MutableLiveData<ArrayList<ModelUserInformation>>() }

    fun getUserInformation() {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.LOADING, "")
        repositoryViewModel.retrieveUserInformationData(this)
    }

    /**
     * Success response callback from Repository
     * @param data for get updated list from API
     */
    override fun onSuccess(data: Any) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.SUCCESS, "")
        data.let { mUserInformationList.value = it as ArrayList<ModelUserInformation> }
    }

    /**
     * Failure response callback from Repository
     * @param error for get error message
     */
    override fun onError(error: String?) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.FAIL, error)
    }
}