package com.techm.optuspoc.view.fragment

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techm.optuspoc.R
import com.techm.optuspoc.adapter.AdapterUserInfo
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.utils.Constant
import com.techm.optuspoc.utils.NetworkConnection.Companion.isNetworkConnected
import com.techm.optuspoc.utils.ResponseStatus
import com.techm.optuspoc.utils.toast
import com.techm.optuspoc.view.activity.UserInformation
import com.techm.optuspoc.viewModel.ViewModelUserInformation
import kotlinx.android.synthetic.main.fragment_user_information.view.*

/**
 * This class is for showing list of Users
 * */
class UserInformationFragment : Fragment(), AdapterUserInfo.OnItemClickListener {
    private lateinit var mAdapter: AdapterUserInfo
    private lateinit var mDataViewModel: ViewModelUserInformation
    private lateinit var builder: AlertDialog.Builder
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_user_information, container, false)
        setupProgressDialog()
        activity?.title = getString(R.string.user_info)
        mDataViewModel = ViewModelProvider(this).get(ViewModelUserInformation::class.java)
        if (activity?.let { isNetworkConnected(it) }!!) {
            showProgressDialog()
            mDataViewModel.getUserInformation()
        } else
            activity!!.toast(getString(R.string.internet_not_connected))

        /**
         * Setting blank adapter for initialize
         */
        mAdapter = context?.let { AdapterUserInfo(ArrayList(), it, this) }!!
        view.user_list.layoutManager = LinearLayoutManager(context)
        view.user_list.adapter = mAdapter

        mDataViewModel.mModelResponseHandle.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResponseStatus.FAIL ->
                    activity!!.toast(getString(R.string.serviceFailureError))
            }
        })
        mDataViewModel.mUserInformationList.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)
            hideProgressDialog()
        })
        return view
    }

    override fun onItemClick(item: ModelUserInformation?) {
        val bundle = Bundle()
        bundle.putParcelable(Constant.userInfo, item)
        var mAlbumInformationFragment = AlbumInformationFragment()
        mAlbumInformationFragment.arguments = bundle
        (context as UserInformation).showFragment(
            mAlbumInformationFragment.let { it },
            AlbumInformationFragment::class.java.name
        )
    }

    private fun setupProgressDialog() {
        builder = AlertDialog.Builder(activity)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    private fun showProgressDialog() {
        dialog.let {
            if (!dialog?.isShowing!!) {
                dialog?.show()
            }
        }

    }

    private fun hideProgressDialog() {
        dialog.let {
            if (dialog?.isShowing!!) {
                dialog?.hide()
                dialog?.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.let {
            if (dialog?.isShowing!!) {
                dialog?.hide()
                dialog?.dismiss()
            }
        }
    }
}
