package com.example.memo.item

data class MemoItemPayload(
    val body: String,
    val onRootClick: () -> Unit,
)
