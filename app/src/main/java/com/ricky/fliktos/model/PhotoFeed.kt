package com.ricky.fliktos.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Generated from http://www.jsonschema2pojo.org/
 */
class PhotoFeed protected constructor(`in`: Parcel) : Parcelable {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("modified")
    @Expose
    var modified: String? = null
    @SerializedName("generator")
    @Expose
    var generator: String? = null
    @SerializedName("items")
    @Expose
    var items: List<Item>? = null

    init {
        this.title = `in`.readValue(String::class.java.classLoader) as String
        this.link = `in`.readValue(String::class.java.classLoader) as String
        this.description = `in`.readValue(String::class.java.classLoader) as String
        this.modified = `in`.readValue(String::class.java.classLoader) as String
        this.generator = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.items, com.ricky.fliktos.model.Item::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(title)
        dest.writeValue(link)
        dest.writeValue(description)
        dest.writeValue(modified)
        dest.writeValue(generator)
        dest.writeList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoFeed> {
        override fun createFromParcel(parcel: Parcel): PhotoFeed {
            return PhotoFeed(parcel)
        }

        override fun newArray(size: Int): Array<PhotoFeed?> {
            return arrayOfNulls(size)
        }
    }

}