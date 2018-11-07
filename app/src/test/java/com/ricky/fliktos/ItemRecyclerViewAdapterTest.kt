package com.ricky.fliktos

import android.widget.ImageView
import com.ricky.fliktos.model.Item
import com.ricky.fliktos.model.Media
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ItemRecyclerViewAdapterTest {
    private val activity = Robolectric.setupActivity(MainActivity::class.java)
    private val items = listOf(
        Item(
            TEST_TITLE,
            "link",
            Media().withM(TEST_URL),
            "2018-11-05T16:13:06-08:00",
            "desc",
            "2018-11-05T16:13:06-08:00",
            "nobody@flickr.com (\"$TEST_AUTHOR\")",
            "authorId",
            TEST_TAG
        )
    )
    private val itemView = activity.layoutInflater.inflate(R.layout.fragment_item, null)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onBindViewHolder() {

        var viewHolder = ItemRecyclerViewAdapter.ViewHolder(itemView)

        var adapter = ItemRecyclerViewAdapter(items, object : ItemRecyclerViewAdapter.ImageLoader {
            override fun loadImage(imageView: ImageView, url: String) {
                Assert.assertThat(url, `is`(TEST_URL))
            }

        })

        adapter.bindViewHolder(viewHolder, 0)


        Assert.assertThat(viewHolder.mTitle.text.toString(), `is`(TEST_TITLE))
        Assert.assertThat(viewHolder.mAuthor.text.toString(), `is`(activity.resources.getString(R.string.author_formatted, TEST_AUTHOR)))
        Assert.assertThat(viewHolder.mTags.text.toString(), `is`(activity.resources.getString(R.string.tags_formatted, TEST_TAG)))
    }

    companion object {
        val TEST_TITLE = "title"
        val TEST_TAG = "tag tag"
        val TEST_AUTHOR = "author"
        val TEST_URL = "url"
    }
}