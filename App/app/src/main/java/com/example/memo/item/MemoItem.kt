package com.example.memo.item

import android.view.View
import com.example.memo.R
import com.example.memo.databinding.ItemMemoBinding
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.GroupieViewHolder

data class MemoItem(
    private val id: String,
    private val body: String,
    private val onRootClick: () -> Unit,
) : BaseItem<ItemMemoBinding, MemoItemPayload>(
    id.hashCode().toLong(),
) {

    override fun getPayload(): MemoItemPayload {
        return MemoItemPayload(
            body,
            onRootClick,
        )
    }

    override fun getChange(newItem: Item<*>): MemoItemPayload? {
        if (newItem !is MemoItem) {
            return null
        }
        return newItem.getPayload()
    }

    override fun update(viewBinding: ItemMemoBinding, payload: MemoItemPayload) {
        viewBinding.memoBodyTextView.text = payload.body
        viewBinding.root.setOnClickListener { payload.onRootClick() }
    }

    override fun getLayout(): Int {
        return R.layout.item_memo
    }

    override fun initializeViewBinding(view: View): ItemMemoBinding {
        return ItemMemoBinding.bind(view)
    }

    override fun unbind(viewHolder: GroupieViewHolder<ItemMemoBinding>) {
        viewHolder.binding.root.setOnClickListener(null)
        super.unbind(viewHolder)
    }
}
