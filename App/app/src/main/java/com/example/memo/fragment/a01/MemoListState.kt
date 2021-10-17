package com.example.memo.fragment.a01

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemoListState(
    val searchText: String,
) : Parcelable {
    companion object {
        val initialValue = MemoListState(
            "",
        )
    }
}
