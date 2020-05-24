package com.techm.optuspoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.techm.optuspoc.callback.ResponseCallback
import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.model.ModelResponseHandle
import com.techm.optuspoc.repository.RepositoryViewModel
import com.techm.optuspoc.utils.ResponseStatus
import org.jetbrains.annotations.NotNull

class ViewModelAlbumInformation(@NotNull application: Application) : AndroidViewModel(application),
    ResponseCallback<Any> {

    private val repositoryViewModel: RepositoryViewModel by lazy { RepositoryViewModel() }
    val mPhotosList: MutableLiveData<ArrayList<ModelPhotosResponse>> by lazy { MutableLiveData<ArrayList<ModelPhotosResponse>>() }
    var mModelResponseHandle = MutableLiveData<ModelResponseHandle>()
    fun getPhotosList() {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.LOADING, "")
        repositoryViewModel.retrievePhotos(this)
    }

    /**
     * Failure response callback from Repository
     * @param error for get error message
     */
    override fun onError(error: String?) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.FAIL, error)
    }

    /**
     * Success response callback from Repository
     * @param data for get updated list from API
     */
    override fun onSuccess(data: Any) {
        mModelResponseHandle.value = ModelResponseHandle(ResponseStatus.SUCCESS, "")
        data.let {
            mPhotosList.value = data as ArrayList<ModelPhotosResponse>
        }
    }
}