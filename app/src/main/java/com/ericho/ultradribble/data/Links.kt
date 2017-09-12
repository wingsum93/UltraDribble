package com.ericho.ultradribble.data

import android.arch.persistence.room.ColumnInfo
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * "links" : {
 * "web" : "http://simplebits.com",
 * "twitter" : "https://twitter.com/simplebits"
 * }
 */

data class Links(

        @ColumnInfo(name = "web")
        @SerializedName("web")
        @Expose
        var web: String?,

        @ColumnInfo(name = "twitter")
        @SerializedName("twitter")
        @Expose
        var twitter: String?

) : Parcelable {

    constructor(parcel: Parcel) : this(
            web = parcel.readString(),
            twitter = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(web)
        parcel.writeString(twitter)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Links> {
        override fun createFromParcel(parcel: Parcel): Links {
            return Links(parcel)
        }

        override fun newArray(size: Int): Array<Links?> {
            return arrayOfNulls(size)
        }
    }

}