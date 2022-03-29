package com.example.ktprojectfortuturu.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ktprojectfortuturu.R
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import kotlinx.android.synthetic.main.item_astro_picture.view.*

class PicturesAdapter : RecyclerView.Adapter<PicturesAdapter.PictureViewHolder>() {

    inner class PictureViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)



    private val differCallback = object : DiffUtil.ItemCallback<AstroPicturesDTO>(){
        override fun areItemsTheSame(
            oldItem: AstroPicturesDTO,
            newItem: AstroPicturesDTO
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: AstroPicturesDTO,
            newItem: AstroPicturesDTO
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_astro_picture,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val astro = differ.currentList[position]
        holder.itemView.apply {
            dateTv.text = astro.date
            titleTv.text = astro.title

            setOnClickListener{
                onItemClickListener?.let { it(astro) }
            }
        }

    }

    private var onItemClickListener: ((AstroPicturesDTO) -> Unit)? = null

    fun setOnItemClickListener(listener:(AstroPicturesDTO) -> Unit){
        onItemClickListener = listener
    }


}