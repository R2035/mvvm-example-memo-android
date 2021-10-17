package com.example.memo.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemoId(
    val value: String
) : Parcelable
