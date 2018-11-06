package com.ricky.fliktos

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.ricky.fliktos.controller.PhotoListPresenter
import com.ricky.fliktos.model.Item


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemFragment.OnListFragmentInteractionListener] interface.
 */
class ItemFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var adapter: ItemRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var textEditView: TextInputEditText
    private lateinit var searchGoButton: Button

    private val photoListListener: PhotoListPresenter.Listener = object : PhotoListPresenter.Listener {
        override fun isLoading(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun loaded() {
            adapter!!.notifyDataSetChanged()
        }

        override fun failed() {
            Toast.makeText(context, "Failed to receive data.", Toast.LENGTH_LONG).show()
        }

    }

    private val photoListPresenter: PhotoListPresenter = PhotoListPresenter(photoListListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // List
        recyclerView = view.findViewById(R.id.list)
        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = ItemRecyclerViewAdapter(photoListPresenter.list, listener)
            this@ItemFragment.adapter = adapter as ItemRecyclerViewAdapter

            setHasFixedSize(true)
        }


        // EditText
        textEditView = view.findViewById(R.id.text_input)
        textEditView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                handleTagInput()
                true
            } else
                false
        }

        // Search Button
        searchGoButton = view.findViewById(R.id.search_go_btn)
        searchGoButton.let { _ ->
            searchGoButton.setOnClickListener {
                handleTagInput()
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photoListPresenter.fetch(emptyList())
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun hideKeyboard() {
        (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view!!.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun handleTagInput() {
        val tagList = textEditView.text
            .trim()
            .split(" ,".toRegex())
        photoListPresenter.fetch(tagList)

        recyclerView.scrollToPosition(0)
        textEditView.clearFocus()
        hideKeyboard()
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Item?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
