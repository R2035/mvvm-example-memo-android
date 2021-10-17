package com.example.memo.fragment.a02

import android.os.Parcelable
import com.example.memo.core.model.Memo
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditingMemoArgument(
    val memo: Memo?
) : Parcelable
