package com.kehtolaulu.subcast.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.kehtolaulu.subcast.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

//    private val mOnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_downloads -> {
//                setFragment(homeFragment)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_recycler -> {
//                setFragment(recyclerFragment)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_pager -> {
//                setFragment(pagerFragment)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }
}
