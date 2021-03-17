package ru.geekbrains.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.adapters.CategoryAdapter
import ru.geekbrains.filmapp.adapters.OnItemViewClickListener
import ru.geekbrains.filmapp.databinding.FragmentCategoryBinding
import ru.geekbrains.filmapp.models.Movie
import ru.geekbrains.filmapp.viewmodels.CategoryFragmentViewModel


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryFragmentViewModel


    private val categoryAdapter = CategoryAdapter(object : OnItemViewClickListener{
        override fun onItemClick(movie: Movie) {
            val action = CategoryFragmentDirections.openMovieDetails(movie.id)
            requireView().findNavController().navigate(action)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        categoryViewModel = ViewModelProvider(this).get(CategoryFragmentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(categoryViewModel)
        doInitialization()
        setupTabListener()
    }

    private fun setupTabListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                categoryViewModel.changeTab(position = tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun doInitialization() {
        binding.rvMoviesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
        startObserving()
    }

    private fun startObserving() {
        categoryViewModel.getObservedMovies().observe(viewLifecycleOwner) {
            binding.isLoading = true
            categoryAdapter.clearItems()
            categoryAdapter.addItems(movies = it.results)
            categoryAdapter.notifyDataSetChanged()
            binding.isLoading = false
        }

        categoryViewModel.getPosition().observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(it)?.select()
        }
    }

}