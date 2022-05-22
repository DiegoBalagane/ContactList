package com.example.contactlist

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.contactlist.data.ContactItem

import com.example.contactlist.databinding.FragmentItemBinding
import org.w3c.dom.Text

/**
 * [RecyclerView.Adapter] that can display a [TaskItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyContactRecyclerViewAdapter(
    private val values: List<ContactItem>,
    private val eventListener: ToContactListListener
) : RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val avatarType = item.avatarID
        when (avatarType) {
            1 -> {
                holder.imgView.setImageResource(R.drawable.dxbddc2xkaaqz34)
            }
            2 -> {
                holder.imgView.setImageResource(R.drawable.esta)
            }
            else -> {
                holder.imgView.setImageResource(R.drawable.doge)
            }
        }

        holder.contentView.text = item.nick
        holder.itemContainer.setOnClickListener {
            eventListener.onItemClick(position)
        }
        holder.itemContainer.setOnLongClickListener {
            eventListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.avatarImg
        val contentView: TextView = binding.nickTxt
        val itemContainer: View = binding.root
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}