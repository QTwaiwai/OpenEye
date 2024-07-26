package com.example.lib.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.lib.base.databinding.FragmentBaseBinding


abstract class BaseFragment<vb : ViewBinding> : Fragment() {
    protected var mbinding: vb? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mbinding = getViewBinding()
        return mbinding?.root
    }

    protected abstract fun getViewBinding(): vb
    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}