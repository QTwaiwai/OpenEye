package com.example.lib.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.lib.base.databinding.FragmentBaseBinding


abstract class BaseFragment<vb : ViewBinding> : Fragment() {
    protected val mbinding by lazy {
        getViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViewCreate()
    }

    protected abstract fun afterViewCreate()
    protected abstract fun getViewBinding():vb

}