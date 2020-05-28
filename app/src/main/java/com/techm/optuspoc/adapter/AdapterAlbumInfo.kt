package com.techm.optuspoc.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techm.optuspoc.BR
import com.techm.optuspoc.R
import com.techm.optuspoc.databinding.ItemPhotoInfoBinding
import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.utils.Constant
import com.techm.optuspoc.utils.loadImage


/**
 * This class for handling list item
 * */
class AdapterAlbumInfo(
    var photoList: ArrayList<ModelPhotosResponse>,
    var context: Context,
    var listener: OnItemClickListener
) : RecyclerView.Adapter<AdapterAlbumInfo.ViewHolder>() {

    /**
     * this method is returning the view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemPhotoInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_photo_info,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    /**
     * This method for setting list to current list from another class
     * @param PhotoList for to get updated list
     */
    fun setList(userList: ArrayList<ModelPhotosResponse>) {
        this.photoList = userList
        notifyDataSetChanged()
    }

    /**
     * This method for set data to item.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position], listener, position)
    }

    /**
     * @return the size of the list.
     */
    override fun getItemCount(): Int {
        return photoList.size
    }

    /**
     * Recycler View click listener interface
     * */
    interface OnItemClickListener {
        fun onItemClick(item: ModelPhotosResponse?, position: Int, view: ImageView)
    }

    /**
     * This class for creating items
     */
    class ViewHolder(private val binding: ItemPhotoInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var imageView = itemView.findViewById(R.id.listImage) as ImageView

        fun bind(data: ModelPhotosResponse, listener: OnItemClickListener, position: Int) {
            ViewCompat.setTransitionName(imageView, Constant.transition)
            itemView.setOnClickListener {
                listener.onItemClick(data, position, imageView)
            }
            binding.listImage.loadImage(data.thumbnailUrl.toString())

            binding.setVariable(
                BR.data,
                data
            )
            binding.data = data
            binding.executePendingBindings()
        }
    }
}