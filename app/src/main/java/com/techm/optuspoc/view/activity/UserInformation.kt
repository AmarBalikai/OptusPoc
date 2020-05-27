package com.techm.optuspoc.view.activity


import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.techm.optuspoc.R
import com.techm.optuspoc.view.fragment.UserInformationFragment
class UserInformation : AppCompatActivity() {
    var tag: String = UserInformationFragment::class.java.name
    var name = UserInformationFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)
        showFragment(UserInformationFragment(), tag)
    }

    /**
     * Calling fragment
     * */
    fun showFragment(name: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0)
        if (!fragmentPopped) {
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, name, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.commit()
        }
    }
}
