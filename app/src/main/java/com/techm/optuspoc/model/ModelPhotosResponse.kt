package com.techm.optuspoc.model

import android.os.Parcel
import android.os.Parcelable

/**
 *This class for handling Photo information
 */
class ModelPhotosResponse(var albumId:String?, var id:String?, var title:String?, var url:String?, var thumbnailUrl:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(albumId)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPhotosResponse> {
        override fun createFromParcel(parcel: Parcel): ModelPhotosResponse {
            return ModelPhotosResponse(parcel)
        }

        override fun newArray(size: Int): Array<ModelPhotosResponse?> {
            return arrayOfNulls(size)
        }
    }
}