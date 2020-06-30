package com.example.dogsjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsjetpack.R
import com.example.dogsjetpack.databinding.RvItemBinding
import com.example.dogsjetpack.model.DogModel
import com.example.dogsjetpack.util.ClickListener
import com.example.dogsjetpack.view.ListFragmentDirections
import kotlinx.android.synthetic.main.rv_item.view.*

class DogAdapter(var dogList: ArrayList<DogModel>): RecyclerView.Adapter<DogAdapter.MyViewHolder>(), ClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = DataBindingUtil.inflate<RvItemBinding>(LayoutInflater.from(parent.context), R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var dataBinding = DataBindingUtil.findBinding<RvItemBinding>(holder.itemView)
        dataBinding?.dogItem = dogList[position]
        dataBinding?.click = this
    }

    class MyViewHolder(itemView: RvItemBinding) : RecyclerView.ViewHolder(itemView.root)

    // Delete all datas from dogList and add new list to dogList
    fun updateList(newList: List<DogModel>) {
        dogList.clear()
        dogList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onClickListener(v: View) {
        var id = v.txtId.text.toString().toInt() // if from txtId in rvItem
        Navigation.findNavController(v).navigate(ListFragmentDirections.actionListFragmentToDogFragment(id))
    }
}