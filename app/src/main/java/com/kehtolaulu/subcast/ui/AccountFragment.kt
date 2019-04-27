package com.kehtolaulu.subcast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kehtolaulu.subcast.R

class AccountFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_account, container, false)

    override fun onStart() {
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountFragment()
    }
}
