package com.example.dogs.breeds.ui.breedphotos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.pbasualdo.dogs.databinding.FragmentBreedPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedPhotosFragment : Fragment() {
    private val viewModel: BreedPhotosViewModel by viewModels()
    private val photosArgs: BreedPhotosFragmentArgs by navArgs()
    private val photoAdapter: BreedImageAdapter = BreedImageAdapter().apply {
        setOnClickListener {
            val action = BreedPhotosFragmentDirections.actionBreedPhotosFragmentToBreedPhotoDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    private var dataBinding : FragmentBreedPhotosBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentBreedPhotosBinding.inflate(inflater,  container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return dataBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val breed = photosArgs.breed
        viewModel.getBreedImages(breed)

        viewModel.breedImages.observe(viewLifecycleOwner){
            photoAdapter.setDataSet(it)
        }

        initViews()
    }

    fun initViews(){
        dataBinding?.let {
            it.rvBreedPhotos.layoutManager = GridLayoutManager(requireContext(),2)
            it.rvBreedPhotos.adapter = photoAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding = null
    }

    companion object{
        private const val TAG = "BreedPhotosFragment"
        fun newInstance() = BreedPhotosFragment()
    }
}