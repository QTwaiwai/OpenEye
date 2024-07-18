package com.example.module.found.ui.fragment

import android.graphics.Typeface
import com.example.lib.base.BaseFragment
import com.example.module.found.databinding.FragmentSpecialDetailBinding

class SpecialDetailFragment : BaseFragment<FragmentSpecialDetailBinding>() {
    override fun afterViewCreate() {
        mbinding.tvEnd.setTypeface(null,Typeface.BOLD_ITALIC)
    }

    override fun getViewBinding(): FragmentSpecialDetailBinding =
        FragmentSpecialDetailBinding.inflate(layoutInflater)

}