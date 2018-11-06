package com.ricky.fliktos.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Generated from http://www.jsonschema2pojo.org/
 */
class Media : Parcelable {

    @SerializedName("m")
    @Expose
    var m: String? = null

    protected constructor(`in`: Parcel) {
        this.m = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param m
     */
    constructor(m: String) : super() {
        this.m = m
    }

    fun withM(m: String): Media {
        this.m = m
        return this
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(m)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }

}