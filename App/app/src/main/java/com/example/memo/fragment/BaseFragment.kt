package com.example.memo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Fragmentの基底クラス
 * このアプリで実装するFragmentは全てこのBaseFragmentを継承させる
 */
abstract class BaseFragment<ViewModel : BaseFragmentViewModel, Binding : ViewBinding> : Fragment() {
    protected abstract val viewModel: ViewModel

    private var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.navDirections.collect {
                findNavController().navigate(it)
            }
        }

        lifecycleScope.launch {
            viewModel.pop.collect {
                findNavController().popBackStack()
            }
        }
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, container, false)
        return requireBinding().root
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
    }
}
