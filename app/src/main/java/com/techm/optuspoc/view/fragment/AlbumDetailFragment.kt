package com.techm.optuspoc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.techm.optuspoc.BR
import com.techm.optuspoc.R
import com.techm.optuspoc.databinding.FragmentAlbumDetailBinding
import com.techm.optuspoc.databinding.ItemPhotoInfoBinding
import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.utils.Constant
import com.techm.optuspoc.utils.loadImage
import com.techm.optuspoc.viewModel.ViewModelAlbumInformation
import kotlinx.android.synthetic.main.fragment_album_detail.view.*

/**
 * This class is for showing Album details
 * */
class AlbumDetailFragment : Fragment() {
    lateinit var binding: FragmentAlbumDetailBinding
    private lateinit var mDataViewModel: ViewModelAlbumInformation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view = inflater.inflate(R.layout.fragment_album_detail, container, false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_album_detail,
            container,
            false
        )

        val bundle = arguments
        val mModelPhotosResponse: ModelPhotosResponse? = bundle?.getParcelable(Constant.albumInfo)

        binding.albumImage.loadImage(mModelPhotosResponse?.url.toString())
        binding.setVariable(
            BR.data,
            mModelPhotosResponse
        )
        binding.imageInfo = mModelPhotosResponse
        binding.executePendingBindings()
        /*binding.albumImage.load(mModelPhotosResponse?.url)
        {
            crossfade(true)
            placeholder(R.drawable.no_image)
            error(R.drawable.no_image)
        }*/
        //view.imageInfo.text = mModelPhotosResponse?.title
        /*view.albumId.text = getString(R.string.album_id) + mModelPhotosResponse?.albumId
        view.photoId.text = getString(R.string.photo_id) + mModelPhotosResponse?.id*/
        mDataViewModel = ViewModelProvider(this).get(ViewModelAlbumInformation::class.java)
        return binding.root
    }
}
