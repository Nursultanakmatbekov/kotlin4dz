package com.example.kotlin4dz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.kotlin4dz.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment(), OnItemClickListeners {

    private var binding: FragmentRecyclerBinding? = null
    private val repository = OnePieceRepository()
    private val adapter = OnePieceAdapter(this)
    private var model: OnePieceModel? = null
    private var list: ArrayList<OnePieceModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = repository.getListOfCelebrities()
        adapter.setData(list)
        initialize()
        setOnClickListener()
        addData()
    }

    private fun initialize() {
        binding?.rvListOfName?.adapter = adapter
    }

    private fun setOnClickListener() {
        binding?.btnAdd?.setOnClickListener {
            findNavController().navigate(R.id.action_recyclerFragment_to_thirdFragment)
        }
    }

    override fun onClick(model: OnePieceModel?) {
        val bundle = Bundle()
        bundle.putSerializable("key", model)
        findNavController().navigate(R.id.action_recyclerFragment_to_detailFragment, bundle)
            .toString()
    }

    private fun addData() {
        setFragmentResultListener("bundle") { requestKey, result ->
            if (requestKey == "bundle") {
                model = result.getSerializable("seri") as OnePieceModel?
                model?.let { list?.add(it) }
                adapter.setData(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
