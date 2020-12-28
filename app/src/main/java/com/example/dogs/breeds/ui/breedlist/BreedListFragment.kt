package com.example.dogs.breeds.ui.breedlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pbasualdo.dogs.databinding.BreedListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedListFragment : Fragment() {

    private var binding : BreedListFragmentBinding? = null
    private val viewModel: BreedListViewModel by viewModels()


    private var breedListAdapter = BreedListAdapter().apply {
        setOnClickListener {
            val action = BreedListFragmentDirections.actionBreedListFragmentToBreedPhotosFragment(it.mainBreed)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BreedListFragmentBinding.inflate(inflater,  container, false).apply {
            lifecycleOwner= viewLifecycleOwner
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvBreeds?.adapter = breedListAdapter
        viewModel.allBreeds.observe(viewLifecycleOwner, {
            breedListAdapter.updateBreedList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val TAG = "BreedListFragment"
    }
}
