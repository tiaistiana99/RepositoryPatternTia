package com.chintansoni.android.tiaistiana.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chintansoni.android.tiaistiana.R
import com.chintansoni.android.tiaistiana.model.local.entity.User
import com.chintansoni.android.tiaistiana.view.adapter.UserRecyclerAdapter
import com.chintansoni.android.tiaistiana.view.fragment.DetailFragment
import com.chintansoni.android.tiaistiana.view.fragment.ListFragment

class UsersActivity : AppCompatActivity(), UserRecyclerAdapter.ItemTouchListener {
    override fun onItemClick(user: User) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_up,
                R.anim.slide_down,
                R.anim.slide_up,
                R.anim.slide_down
            )
            .add(R.id.container, DetailFragment.newInstance(user))
            .addToBackStack(detailFragmentTag)
            .commit()
    }

    companion object {
        const val detailFragmentTag = "details_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commit()
        }
    }
}
