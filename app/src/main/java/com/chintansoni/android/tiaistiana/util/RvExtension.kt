package com.chintansoni.android.tiaistiana.util

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setInfiniteScroll(func: RecyclerView.() -> Unit) {
    this.adapter?.let {
        this.addOnScrollListener(object : InfiniteScrollListener(it) {
            override fun fetchNext() {
                func.invoke(this@setInfiniteScroll)
            }
        })
    }
}