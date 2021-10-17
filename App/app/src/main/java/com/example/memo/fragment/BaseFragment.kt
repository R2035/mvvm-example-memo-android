package com.example.memo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

/**
 * Fragmentの基底クラス
 * このアプリで実装するFragmentは全てこのBaseFragmentを継承させる
 */
abstract class BaseFragment<ViewModel : BaseFragmentViewModel, Binding : ViewBinding> : Fragment() {
    protected abstract val viewModel: ViewModel

    private var binding: Binding? = null

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, container, false)
        return requireBinding().root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navDirections.observe(viewLifecycleOwner) {
            navigate(it)
        }
    }

    @CallSuper
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    protected abstract fun inflate(inflater: LayoutInflater, root: ViewGroup?, attachToParent: Boolean): Binding

    protected fun requireBinding(): Binding {
        return binding ?: throw IllegalStateException("binding is null.")
    }

    private fun navigate(navDirections: NavDirections) {
        val navController = this.findNavController()
        navController.navigate(navDirections)
    }
}
