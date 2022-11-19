package com.example.kotlin4dz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kotlin4dz.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var binding : FragmentDetailBinding? = null
    private var model: OnePieceModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        data
    }

    private fun setOnClick() {
        binding?.btnBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private val data: Unit
        get() {
            val argument = arguments
            if (argument != null) {
                model = argument.getSerializable("key") as OnePieceModel?
                binding?.ivFullscreen?.let { it.context?.let { it1 -> Glide.with(it1).load(model?.imageUri).into(binding?.ivFullscreen!!) } }
                binding?.tvName?.text = model?.name
                binding?.tvAge?.text = model?.age.toString()
                binding?.tvI?.text = model?.i
            }
        }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}