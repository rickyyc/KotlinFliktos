package com.ricky.fliktos


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ricky.fliktos.model.Item
import kotlinx.android.synthetic.main.fragment_item.view.*

class ItemRecyclerViewAdapter(
    private val mValues: List<Item>,
    private val mImageLoader: ImageLoader
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        // title
        item.title?.let {
            holder.mTitle.text = it
        }

        // author
        item.author?.let {
            holder.mAuthor.text =
                    holder.itemView.context.resources.getString(R.string.author_formatted, parseAuthor(it))
        }

        // tags
        item.tags?.let {
            holder.mTags.text = holder.itemView.context.resources.getString(R.string.tags_formatted, it)
        }

        // photo
        item.media?.m?.let {
            mImageLoader.loadImage(holder.mPhoto, it)
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    private fun parseAuthor(input: String): String {
        // simple way to extract the author display name
        val start = input.indexOf("(\"")
        val end = input.indexOf("\")")

        return input.substring(start + 2, end)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.title
        val mPhoto: ImageView = mView.photo
        val mAuthor: TextView = mView.author
        val mTags: TextView = mView.tags
    }

    interface ImageLoader {
        fun loadImage(imageView: ImageView, url: String)
    }
}
