package ru.testermast.simplerecyclerview

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.testermast.simplerecyclerview.databinding.ItemDataBinding

class SimpleAdapter: RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>(){

    private var elements = ArrayList<String>(10)

    init {
        for (i in 0..9){
            elements.add(i, "element #$i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(layoutInflater, parent, false)
        val colorBackgroundItem = getBackgroundColor(parent, viewType)
        binding.root.setBackgroundColor(colorBackgroundItem)
        return SimpleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.binding.textViewItem.text = elements[position]
    }

    override fun getItemCount(): Int = elements.size

    // toggles the background color of neighboring elements
    override fun getItemViewType(position: Int): Int =
        if ((position % 2) == 0) COLOR_GREEN else COLOR_RED

    private fun getBackgroundColor(parent: ViewGroup, viewType: Int): Int {
        return if (viewType == COLOR_GREEN) parent.resources.getColor(R.color.textViewBackColorOne, null)
        else parent.resources.getColor(R.color.textViewBackColorTwo, null)
    }

    companion object{
        const val COLOR_GREEN = 0
        const val COLOR_RED = 5
    }

    class SimpleViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root)
}