package com.dheeraj.composemvvm.fragment

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    // Callback Interface Sort
    interface SortCallback {
        fun onSort(isAscend: Boolean)
    }

    private var sortCallback: SortCallback? = null



    // Binding interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SortCallback) {
            sortCallback = context
        } else {
            throw RuntimeException("$context Need to Implement SortCallback Interface")
        }
    }

    // unbind
    override fun onDetach() {
        super.onDetach()
        sortCallback = null
    }

    // sort
    abstract fun sortData(isAscend: Boolean)
}