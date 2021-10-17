package com.example.memo.fragment.a02

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditingMemoState(
    val body: String,
) : Parcelable {
    companion object {
        val initialValue = EditingMemoState(
            "",
        )
    }
}
