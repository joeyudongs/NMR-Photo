package com.dheeraj.composemvvm.fragment

import CreditCardScreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dheeraj.composemvvm.viewmodel.CreditCardViewModel

class ComposeFragment : Fragment() {


    private val viewModel: CreditCardViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val view = inflater.inflate(R.layout.fragment_compose, container, false)
//        val composeView = view.findViewById<ComposeView>(R.id.fragment_c)
//
//        composeView.apply {
//            // Dispose of the Composition when the view's LifecycleOwner
//            // is destroyed
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                // In Compose world
//                MaterialTheme {
//                    Text(
//                        modifier = Modifier.background(color = Color.Red),
//                        text = "Hello Compose!"
//                    )
//                }
//            }
//        }
//
//        return view

        return ComposeView(requireContext()).apply {
            setContent {
                CreditCardScreen(viewModel)
            }
        }
    }



}