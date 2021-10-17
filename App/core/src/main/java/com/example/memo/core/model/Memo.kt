package com.example.memo.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Memo(
    val id: MemoId,
    val body: String,
) : Parcelable
