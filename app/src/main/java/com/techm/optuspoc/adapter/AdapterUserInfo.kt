package com.techm.optuspoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
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
    var userList: ArrayList<ModelUserInformation>,
    var context: Context,
    var listener: OnItemClickListener
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
     * @param UserList for to get updated list
     */
    fun setList(userList: ArrayList<ModelUserInformation>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    /**
     * This method for set data to item.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position], listener)
    }
    /**
     * @return the size of the list.
     */
    override fun getItemCount(): Int {
        return userList.size
    }
    /**
     * Item click listener
     * */
    interface OnItemClickListener {
        fun onItemClick(item: ModelUserInformation?)
    }

    /**
     * This class for creating items
     */
    class ViewHolder(private val binding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ModelUserInformation, listener: OnItemClickListener) {
            itemView.setOnClickListener(View.OnClickListener {
                listener.onItemClick(data)
            })
            binding.setVariable(
                BR.data,
                data
            )
            binding.data = data
            binding.executePendingBindings()
        }
    }
}