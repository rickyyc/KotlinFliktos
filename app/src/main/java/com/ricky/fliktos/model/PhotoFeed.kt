package com.ricky.fliktos.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Generated from http://www.jsonschema2pojo.org/
 */
class PhotoFeed : Parcelable {

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

    protected constructor(`in`: Parcel) {
        this.title = `in`.readValue(String::class.java.classLoader) as String
        this.link = `in`.readValue(String::class.java.classLoader) as String
        this.description = `in`.readValue(String::class.java.classLoader) as String
        this.modified = `in`.readValue(String::class.java.classLoader) as String
        this.generator = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(this.items, com.ricky.fliktos.model.Item::class.java!!.getClassLoader())
    }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param title
     * @param items
     * @param description
     * @param link
     * @param generator
     * @param modified
     */
    constructor(
        title: String,
        link: String,
        description: String,
        modified: String,
        generator: String,
        items: List<Item>
    ) : super() {
        this.title = title
        this.link = link
        this.description = description
        this.modified = modified
        this.generator = generator
        this.items = items
    }

    fun withTitle(title: String): PhotoFeed {
        this.title = title
        return this
    }

    fun withLink(link: String): PhotoFeed {
        this.link = link
        return this
    }

    fun withDescription(description: String): PhotoFeed {
        this.description = description
        return this
    }

    fun withModified(modified: String): PhotoFeed {
        this.modified = modified
        return this
    }

    fun withGenerator(generator: String): PhotoFeed {
        this.generator = generator
        return this
    }

    fun withItems(items: List<Item>): PhotoFeed {
        this.items = items
        return this
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