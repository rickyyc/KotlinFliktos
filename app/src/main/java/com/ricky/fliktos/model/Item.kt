package com.ricky.fliktos.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Generated from http://www.jsonschema2pojo.org/
 */
class Item : Parcelable {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("media")
    @Expose
    var media: Media? = null
    @SerializedName("date_taken")
    @Expose
    var dateTaken: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("published")
    @Expose
    var published: String? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("author_id")
    @Expose
    var authorId: String? = null
    @SerializedName("tags")
    @Expose
    var tags: String? = null

    protected constructor(`in`: Parcel) {
        this.title = `in`.readValue(String::class.java.classLoader) as String
        this.link = `in`.readValue(String::class.java.classLoader) as String
        this.media = `in`.readValue(Media::class.java.classLoader) as Media
        this.dateTaken = `in`.readValue(String::class.java.classLoader) as String
        this.description = `in`.readValue(String::class.java.classLoader) as String
        this.published = `in`.readValue(String::class.java.classLoader) as String
        this.author = `in`.readValue(String::class.java.classLoader) as String
        this.authorId = `in`.readValue(String::class.java.classLoader) as String
        this.tags = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor()

    /**
     *
     * @param tags
     * @param author
     * @param dateTaken
     * @param title
     * @param description
     * @param link
     * @param published
     * @param media
     * @param authorId
     */
    constructor(
        title: String,
        link: String,
        media: Media,
        dateTaken: String,
        description: String,
        published: String,
        author: String,
        authorId: String,
        tags: String
    ) : super() {
        this.title = title
        this.link = link
        this.media = media
        this.dateTaken = dateTaken
        this.description = description
        this.published = published
        this.author = author
        this.authorId = authorId
        this.tags = tags
    }

    fun withTitle(title: String): Item {
        this.title = title
        return this
    }

    fun withLink(link: String): Item {
        this.link = link
        return this
    }

    fun withMedia(media: Media): Item {
        this.media = media
        return this
    }

    fun withDateTaken(dateTaken: String): Item {
        this.dateTaken = dateTaken
        return this
    }

    fun withDescription(description: String): Item {
        this.description = description
        return this
    }

    fun withPublished(published: String): Item {
        this.published = published
        return this
    }

    fun withAuthor(author: String): Item {
        this.author = author
        return this
    }

    fun withAuthorId(authorId: String): Item {
        this.authorId = authorId
        return this
    }

    fun withTags(tags: String): Item {
        this.tags = tags
        return this
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(title)
        dest.writeValue(link)
        dest.writeValue(media)
        dest.writeValue(dateTaken)
        dest.writeValue(description)
        dest.writeValue(published)
        dest.writeValue(author)
        dest.writeValue(authorId)
        dest.writeValue(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Item{" +
                "title='" + title + '\''.toString() +
                ", link='" + link + '\''.toString() +
                ", media=" + media +
                ", dateTaken='" + dateTaken + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", published='" + published + '\''.toString() +
                ", author='" + author + '\''.toString() +
                ", authorId='" + authorId + '\''.toString() +
                ", tags='" + tags + '\''.toString() +
                '}'.toString()
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}