package com.example.kotlin4dz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.kotlin4dz.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private var binding: FragmentThirdBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickAdd()
    }

    private fun onClickAdd() {
        binding?.btAdd?.setOnClickListener {
            val url = binding?.edUrl?.text.toString().trim { it <= ' ' }
            val name = binding?.edName?.text.toString()
            val i = binding?.edI?.text.toString()
            val age = binding?.edAge?.text.toString()
            val bundle = Bundle()

            if (url.isEmpty() && name.isEmpty() && i.isEmpty() && age.isEmpty()) {
                binding?.edUrl?.error = "Введите Url"
                binding?.edName?.error = "Введите Name"
                binding?.edI?.error = "Введите I"
                binding?.edAge?.error = "Введите Age"
            } else if (url.isEmpty()) {
                binding?.edUrl?.error = "Введите URL"
            } else if (name.isEmpty()) {
                binding?.edName?.error = "Введите Name"
            } else if (i.isEmpty()) {
                binding?.edI?.error = "Введите I"
            } else if (age.isEmpty()) {
                binding?.edAge?.error = "Введите Age"
            } else {
                val model = OnePieceModel(url, name, age.toInt(), i, "#44FF00")
                bundle.putSerializable("seri", model)
                setFragmentResult("bundle", bundle)
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}