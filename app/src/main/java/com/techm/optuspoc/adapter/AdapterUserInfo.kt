package com.techm.optuspoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techm.optuspoc.BR
import com.techm.optuspoc.R
import com.techm.optuspoc.databinding.ItemUserInfoBinding
import com.techm.optuspoc.model.ModelUserInformation

/**
 * This class for handling list item
 * */
class AdapterUserInfo(
    private var userList: ArrayList<ModelUserInformation>,
    private var context: Context
) : RecyclerView.Adapter<AdapterUserInfo.ViewHolder>() {
    /**
     * this method is returning the view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemUserInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_user_info,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    /**
     * This method for setting list to current list from another class
     * @param countryList for to get updated list
     */
    fun setList(userList: ArrayList<ModelUserInformation>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    /**
     * This method for set data to item.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    /**
     * @return the size of the list.
     */
    override fun getItemCount(): Int {
        return userList.size
    }

    /**
     * This class for creating items
     */
    class ViewHolder(private val binding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ModelUserInformation) {

            binding.setVariable(
                BR.data,
                data
            )
            binding.data = data
            binding.executePendingBindings()
        }
    }
}