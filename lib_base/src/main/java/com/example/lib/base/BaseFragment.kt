package com.example.lib.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lib.base.databinding.FragmentBaseBinding


open class BaseFragment : Fragment() {
    private var mbinding: FragmentBaseBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentBaseBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return mbinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}