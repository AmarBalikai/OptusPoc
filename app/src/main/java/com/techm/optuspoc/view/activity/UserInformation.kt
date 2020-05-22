package com.techm.optuspoc.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.techm.optuspoc.R
import com.techm.optuspoc.view.fragment.UserInformationFragment

class UserInformation : AppCompatActivity() {
    var tag:String= UserInformationFragment::class.java.name
    var name= UserInformationFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        val fragmentManager: FragmentManager = supportFragmentManager
        // check if the fragment is in back stack
        // check if the fragment is in back stack
        val fragmentPopped: Boolean = fragmentManager.popBackStackImmediate(tag, 0)
        if (fragmentPopped) {
            // fragment is pop from backStack
        } else {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, name, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.commit()
        }
    }
}
