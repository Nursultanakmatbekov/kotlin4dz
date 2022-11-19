package com.example.kotlin4dz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin4dz.databinding.ItemNameBinding

class OnePieceAdapter(onItemClickListener: OnItemClickListeners) :
    RecyclerView.Adapter<OnePieceAdapter.CelebritiesViewHolder>(), View.OnClickListener {

    private var mutableList: MutableList<OnePieceModel>? = null
    private val onItemClickListener: OnItemClickListeners

    fun setData(listCelebrity: MutableList<OnePieceModel>?) {
        this.mutableList = listCelebrity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebritiesViewHolder {
        return CelebritiesViewHolder(
            ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CelebritiesViewHolder, position: Int) {
        holder.itemView.setOnClickListener(this)
        holder.onBind(mutableList!![position])
    }

    override fun getItemCount(): Int {
        return mutableList!!.size
    }

    init {
        this.onItemClickListener = onItemClickListener
    }

    override fun onClick(view: View) {
        onItemClickListener.onClick(view.tag as OnePieceModel)
    }

    class CelebritiesViewHolder(private val binding: ItemNameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(celebrities: OnePieceModel) {
            Glide.with(binding.ivImage.context).load(celebrities.imageUri).into(binding.ivImage)
            binding.tvName.text = celebrities.name
            binding.tvAge.text = celebrities.age.toString()
            binding.tvI.text = celebrities.i
            itemView.tag = celebrities
        }
    }
}