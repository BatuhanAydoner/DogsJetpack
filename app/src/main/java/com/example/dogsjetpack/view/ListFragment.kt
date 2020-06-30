package com.example.dogsjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dogsjetpack.R
import com.example.dogsjetpack.adapter.DogAdapter
import com.example.dogsjetpack.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private var dogAdapter = DogAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        listViewModel.setList()

        setRecyclerview()
        observeLiveData()
    }

    // Observe live datas for listViewModel
    private fun observeLiveData() {
        listViewModel.dogList.observe(viewLifecycleOwner, Observer {
            it?.let {
                dogAdapter.updateList(it)
            }
        })
    }

    // Init rvDogs
    private fun setRecyclerview() {
        rvDogs.apply {
            adapter = dogAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}