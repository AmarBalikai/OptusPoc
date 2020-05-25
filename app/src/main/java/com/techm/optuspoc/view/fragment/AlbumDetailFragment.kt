package com.techm.optuspoc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.bumptech.glide.Glide
import com.techm.optuspoc.R
import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.utils.Constant
import com.techm.optuspoc.viewModel.ViewModelAlbumInformation
import kotlinx.android.synthetic.main.fragment_album_detail.view.*

/**
 * This class is for showing Album details
 * */
class AlbumDetailFragment : Fragment() {
    private lateinit var mDataViewModel: ViewModelAlbumInformation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_album_detail, container, false)
        val view = inflater.inflate(R.layout.fragment_album_detail, container, false)
        activity?.title = getString(R.string.album_detail)
        val bundle = arguments
        val mModelPhotosResponse: ModelPhotosResponse? = bundle?.getParcelable(Constant.albumInfo)
        //var transitionName: String? = bundle?.getString(Constant.transition)
        //view.albumImage.transitionName = transitionName

        /*Glide.with(this)
            .load(mModelPhotosResponse?.url)
            .placeholder(R.drawable.no_image)
            .into(view.albumImage)*/
        view.albumImage.load(mModelPhotosResponse?.url)
        {
            crossfade(true)
            placeholder(R.drawable.no_image)
            error(R.drawable.no_image)
        }
        view.imageInfo.text = mModelPhotosResponse?.title
        view.albumId.text = getString(R.string.album_id) + mModelPhotosResponse?.albumId
        view.photoId.text = getString(R.string.photo_id) + mModelPhotosResponse?.id
        mDataViewModel = ViewModelProvider(this).get(ViewModelAlbumInformation::class.java)
        return view
    }
}
