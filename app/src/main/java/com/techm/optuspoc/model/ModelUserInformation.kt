package com.techm.optuspoc.model

import android.os.Parcel
import android.os.Parcelable

/**
 *This class for handling User information
 */
class ModelUserInformation(var website: String?, var address: AddressInformation?, var phone: String?, var name: String?, var company: CompanyInformation?, var id: String?, var email: String?, var username: String?,var error:String="") :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("address"),
        parcel.readString(),
        parcel.readString(),
        TODO("company"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }

    override fun describeContents()=0

    companion object CREATOR : Parcelable.Creator<ModelUserInformation> {
        override fun createFromParcel(parcel: Parcel): ModelUserInformation {
            return ModelUserInformation(parcel)
        }

        override fun newArray(size: Int): Array<ModelUserInformation?> {
            return arrayOfNulls(size)
        }
    }
}
