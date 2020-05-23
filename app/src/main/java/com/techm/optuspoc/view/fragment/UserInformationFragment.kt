package com.techm.optuspoc.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techm.optuspoc.R
import com.techm.optuspoc.adapter.AdapterUserInfo
import com.techm.optuspoc.viewModel.ViewModelUserInformation
import kotlinx.android.synthetic.main.fragment_user_information.*
import kotlinx.android.synthetic.main.fragment_user_information.view.*


class UserInformationFragment : Fragment() {
    private lateinit var mAdapter:AdapterUserInfo
    private lateinit var mDataViewModel: ViewModelUserInformation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_user_information, container, false)
        mDataViewModel = ViewModelProvider(this).get(ViewModelUserInformation::class.java)
        activity?.title = getString(R.string.user_info)

        /**
         * Setting blank adapter for initialize
         */
        mAdapter = context?.let { AdapterUserInfo(ArrayList(), it) }!!
        view.user_list.layoutManager = LinearLayoutManager(context)
        view.user_list.adapter = mAdapter

        mDataViewModel.mUserInformationList.observe(viewLifecycleOwner, Observer {
            mAdapter.setList(it)
        })
        return view
    }

}
