package com.example.life_renewed.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.life_renewed.databinding.FragmentLinksItemBinding
import com.example.life_renewed.model.LinksModel

class LinksAdapter : RecyclerView.Adapter<LinksAdapter.LinksViewHolder>() {

    init {
        // Essential: Prevents resetting to top if data loads asynchronously
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    var linksModels = emptyList<LinksModel>()

    class LinksViewHolder(val binding: FragmentLinksItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LinksViewHolder {
        val binding =
            FragmentLinksItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        holder.binding.nameText.text = linksModels[position].name
        holder.binding.emailText.text = linksModels[position].email
        holder.binding.iconImage.setImageResource(linksModels[position].icon)
    }

    override fun getItemCount() = linksModels.size

    fun submitLinks(links: List<LinksModel>) {
        linksModels = links
        notifyDataSetChanged()
    }

}