package com.vel.koloreddit.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vel.koloreddit.R
import kotlinx.android.synthetic.main.bottom_sheet_fragment.*

open class FeedFilterBottomFragment : BottomSheetDialogFragment(), FeedFilterClickListener {
    lateinit var mOnInputSelected: OnInputSelected

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val otherStrings = ArrayList<String>()
        otherStrings.add("Hot")
        otherStrings.add("New")
        otherStrings.add("Top")
        otherStrings.add("Raising")
        if (otherStrings.isNotEmpty()) {
            val denomAdapter = FeedFilterAdapter(otherStrings, this)
            val layoutManager = LinearLayoutManager(requireContext())
            denom_recyclerView.layoutManager = layoutManager
            denom_recyclerView.itemAnimator = DefaultItemAnimator()
            denom_recyclerView.adapter = denomAdapter
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputSelected = targetFragment as OnInputSelected
        } catch (e: ClassCastException) {
            Log.e("onAttach", e.message.toString())

        }
    }

    interface OnInputSelected {
        fun sendInput(filterUrl: String,position: Int)
    }

    override fun DenomRecyclerClick(view: View, filterurl: String, position: Int) {
        mOnInputSelected!!.sendInput(filterurl,position)
    }
}