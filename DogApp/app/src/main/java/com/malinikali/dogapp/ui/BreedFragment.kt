package com.malinikali.dogapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.malinikali.dogapp.R
import com.malinikali.dogapp.adapter.BreedAdapter
import com.malinikali.dogapp.databinding.FragmentBreedBinding
import com.malinikali.dogapp.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment : Fragment() {

    private var _binding: FragmentBreedBinding? = null
    private val binding get() = _binding!!
    private lateinit var breedAdapter:BreedAdapter
    private val viewModel:DogViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBreedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breedAdapter = BreedAdapter()

        binding.rcBreeds.apply {
            adapter = breedAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        viewModel.breeds.observe(this.viewLifecycleOwner,{
            items ->
            items.let {
                breedAdapter.breedList = items
            }
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_search,menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null){
                    binding.rcBreeds.scrollToPosition(0)
                    viewModel.addSearchName(newText)
                    searchView.clearFocus()

                }
                return true
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}