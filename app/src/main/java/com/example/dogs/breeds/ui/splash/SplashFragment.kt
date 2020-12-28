package com.example.dogs.breeds.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pbasualdo.dogs.R
import com.pbasualdo.dogs.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class SplashFragment: Fragment() {

    private val viewModel: SplashViewModel by viewModels()
    private var binding: FragmentSplashBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.percentageLD.observe(viewLifecycleOwner){ percentage ->
            binding?.let {
                it.pbDummy.progress = percentage
                if (percentage == 100){
                    findNavController().navigate(R.id.action_splashFragment_to_breedListFragment)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.startCountDown(5)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}