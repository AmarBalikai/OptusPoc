package com.techm.optuspoc.view.fragment

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techm.optuspoc.R
import com.techm.optuspoc.adapter.AdapterAlbumInfo
import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.utils.Constant
import com.techm.optuspoc.utils.NetworkConnection
import com.techm.optuspoc.utils.ResponseStatus
import com.techm.optuspoc.utils.toast
import com.techm.optuspoc.view.activity.UserInformation
import com.techm.optuspoc.viewModel.ViewModelAlbumInformation
import kotlinx.android.synthetic.main.fragment_user_information.view.*
import java.util.stream.Collectors

/**
 * This class is for showing album list
 * */
class AlbumInformationFragment : Fragment(), AdapterAlbumInfo.OnItemClickListener {
    private lateinit var mAdapter: AdapterAlbumInfo
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var mDataViewModel: ViewModelAlbumInformation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_album_information, container, false)
        mDataViewModel = ViewModelProvider(this).get(ViewModelAlbumInformation::class.java)
        setupProgressDialog()
        activity?.title = getString(R.string.album_info)
        var bundle = arguments
        var mModelUserInformation: ModelUserInformation? = bundle?.getParcelable(Constant.userInfo)

        /**
         * Setting blank adapter for initialize
         */
        mAdapter = context?.let { AdapterAlbumInfo(ArrayList(), it, this) }!!
        view.user_list.layoutManager = LinearLayoutManager(context)
        view.user_list.adapter = mAdapter
        if (activity?.let { NetworkConnection.isNetworkConnected(it) }!!) {
            showProgressDialog()
            mDataViewModel.getPhotosList()
        } else
            activity!!.toast(getString(R.string.internet_not_connected))

        mDataViewModel.mModelResponseHandle.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResponseStatus.FAIL ->
                    activity!!.toast(getString(R.string.serviceFailureError))
            }
        })
        mDataViewModel.mPhotosList.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            mAdapter.setList(filterList(it, mModelUserInformation))
        })
        return view
    }

    /**
     * get list of object matching with user ID
     * */
    private fun filterList(
        photoList: ArrayList<ModelPhotosResponse>,
        mModelUserInformation: ModelUserInformation?
    ): ArrayList<ModelPhotosResponse> {
        var mAdapterPhotoList = ArrayList<ModelPhotosResponse>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return photoList.stream()
                .filter { p -> p.albumId == mModelUserInformation?.id }
                .map { pm -> pm }
                .collect(Collectors.toList()) as ArrayList<ModelPhotosResponse>
        } else {
            for (list in photoList) {
                if (list.albumId == mModelUserInformation?.id) {
                    mAdapterPhotoList.add(list)
                }
            }
        }
        return mAdapterPhotoList
    }

    /**
     *Recycle item click listener
     * */
    override fun onItemClick(item: ModelPhotosResponse?, position: Int, view: ImageView) {
        val bundle = Bundle()
        //    var transitionName = "transition$position"
        bundle.putParcelable(Constant.albumInfo, item)
        bundle.putString(Constant.transition, ViewCompat.getTransitionName(view))
        var mAlbumDetailFragment = AlbumDetailFragment()
        mAlbumDetailFragment.arguments = bundle
        (context as UserInformation).showFragment(
            mAlbumDetailFragment.let { it },
            AlbumDetailFragment::class.java.name
        )
        /*showFragmentWithTransition(
            this,
            mAlbumDetailFragment.let { it },
            AlbumInformationFragment::class.java.name,
            view,
            transitionName)*/
    }

    private fun setupProgressDialog() {
        builder = AlertDialog.Builder(activity)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    private fun showProgressDialog() {
        dialog.let {
            if (!dialog.isShowing) {
                dialog.show()
            }
        }
    }

    private fun hideProgressDialog() {
        dialog.let {
            if (dialog.isShowing) {
                dialog.hide()
                dialog.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.let {
            if (dialog.isShowing) {
                dialog.hide()
                dialog.dismiss()
            }
        }
    }

    fun showFragmentWithTransition(
        current: Fragment,
        newFragment: Fragment,
        tag: String?,
        sharedView: ImageView?,
        sharedElementName: String?
    ) {
        val fragmentManager = activity?.supportFragmentManager
        // check if the fragment is in back stack
        val fragmentPopped = fragmentManager?.popBackStackImmediate(tag, 0)
        if (fragmentPopped!!) {
            // fragment is pop from backStack
        } else {
            current.sharedElementReturnTransition =
                TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
            current.exitTransition = TransitionInflater.from(context)
            //.inflateTransition(R.transition.no_transition)
            newFragment.sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
            newFragment.enterTransition = TransitionInflater.from(context)
            //.inflateTransition(R.transition.no_transition)
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, newFragment, tag)
            fragmentTransaction.addToBackStack(tag)
            sharedView?.let {
                fragmentTransaction.addSharedElement(
                    it,
                    ViewCompat.getTransitionName(it)!!
                )
            }
            fragmentTransaction.commit()
        }
    }
}
