package com.example.dogs.breeds.ui.breedphotodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pbasualdo.dogs.databinding.FragmentBreedPhotoDetailBinding
import com.squareup.picasso.Picasso

class BreedPhotoDetailFragment: Fragment() {
    private var binding: FragmentBreedPhotoDetailBinding? = null
    private val args: BreedPhotoDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBreedPhotoDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoLink = args.photoLink
        binding?.let {
            Picasso.get().load(photoLink)
                .into(it.ivBreedDetail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}