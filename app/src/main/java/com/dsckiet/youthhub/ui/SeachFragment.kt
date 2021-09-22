package com.dsckiet.youthhub.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.Adapters.SearchAdapter
import com.example.youthhub.R
import com.example.youthhub.Repo.Repository
import com.example.youthhub.ViewModel.SearchViewModel
import com.example.youthhub.ViewModel.SearchViewModelFactory
import com.example.youthhub.databinding.FragmentPlaylistBinding
import com.example.youthhub.databinding.FragmentSeachBinding


class SeachFragment : Fragment() {

    private val SearchAdapter: _root_ide_package_.com.dsckiet.youthhub.Adapters.SearchAdapter by lazy{
        _root_ide_package_.com.dsckiet.youthhub.Adapters.SearchAdapter(
            requireContext()
        )
    }
    private lateinit var binding: FragmentSeachBinding
    private lateinit var viewModel: SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_seach,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBackBtn.setOnClickListener{

            findNavController().navigate(R.id.action_seachFragment_to_homeFragment)

        }
        val searchrecyclerView: RecyclerView = view.findViewById(R.id.seach_recview)
        val repository = Repository(this.requireContext())
        val vmf = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this,vmf).get(SearchViewModel::class.java)

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(searchit: String?): Boolean {
                    viewModel.getSearchItem("snippet",searchit.toString())

                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.getSearchItem("snippet",newText.toString())
                    return false
                }
            }
        )

        searchrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchrecyclerView.adapter = SearchAdapter

        viewModel.searched.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful)
                SearchAdapter.citysetStateWiseTracker(it.body()?.snippet!!)

            Log.d("BOLTy","success"+it.toString())


        })


    }

}