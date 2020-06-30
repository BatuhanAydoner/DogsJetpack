package com.example.dogsjetpack.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dogsjetpack.R
import com.example.dogsjetpack.databinding.FragmentDogBinding
import com.example.dogsjetpack.model.DogModel
import com.example.dogsjetpack.viewmodel.DogViewModel

class DogFragment : Fragment() {

    private var uuid = 0

    private lateinit var view: FragmentDogBinding

    private lateinit var dogViewModel: DogViewModel
    private lateinit var dog: DogModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(inflater, R.layout.fragment_dog, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            uuid = DogFragmentArgs.fromBundle(it).uuid
        }

        Log.e("Error", "Id : " + uuid)

        dogViewModel = ViewModelProvider(this).get(DogViewModel::class.java)
        dogViewModel.fetch(uuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        dogViewModel.dog.observe(viewLifecycleOwner, Observer {
            dog = it
            it?.let {
                view.dog = dog
            }
        })
    }
}