package com.example.dogadopt.ui.doglist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dogadopt.ui.dogdetail.DogDetailActivity
import com.example.dogadopt.ui.theme.MyTheme

class DogListFragment : Fragment() {

    private val viewModel by viewModels<DogListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view as ComposeView
        viewModel.dogListLiveData.observe(viewLifecycleOwner) { dogList ->
            dogList ?: return@observe
            composeView.setContent {
                MyTheme {
                    DogList(dogList) { dog ->
                        startActivity(
                            Intent(requireContext(), DogDetailActivity::class.java).apply {
                                putExtra("dog", dog)
                            }
                        )
                    }
                }
            }
        }
    }
}