package com.example.module.found.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.module.found.R
import com.example.module.found.databinding.FragmentFoundBinding


class FoundFragment : Fragment() {
    private val mbinding: FragmentFoundBinding by lazy {
        FragmentFoundBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_found, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoundFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoundFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}