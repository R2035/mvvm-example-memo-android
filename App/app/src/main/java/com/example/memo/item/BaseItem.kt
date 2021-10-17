package com.example.memo.item

import androidx.viewbinding.ViewBinding
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

abstract class BaseItem<V : ViewBinding, Payload>(
    private val id: Long
) : BindableItem<V>() {

    /**
     * 本来は新旧のアイテムの差分を取得するが簡略化のために全ての値をまとめて取得する
     */
    abstract fun getPayload(): Payload

    protected abstract fun getChange(newItem: Item<*>): Payload?

    protected abstract fun update(viewBinding: V, payload: Payload)

    abstract override fun getLayout(): Int

    final override fun getId(): Long {
        return id
    }

    final override fun getChangePayload(newItem: Item<*>): Any? {
        return getChange(newItem)
    }

    final override fun bind(viewBinding: V, position: Int) {
        update(viewBinding, getPayload())
    }

    final override fun bind(viewBinding: V, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            update(viewBinding, getPayload())
        }
        payloads.forEach { payload ->
            @Suppress("UNCHECKED_CAST")
            update(viewBinding, (payload as Payload))
        }
    }
}
