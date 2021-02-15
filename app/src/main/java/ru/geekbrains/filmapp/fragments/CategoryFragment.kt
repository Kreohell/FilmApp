package ru.geekbrains.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.adapters.CategoryAdapter
import ru.geekbrains.filmapp.adapters.OnItemViewClickListener
import ru.geekbrains.filmapp.databinding.FragmentCategoryBinding
import ru.geekbrains.filmapp.models.OriginalSourcePreview
import ru.geekbrains.filmapp.viewmodels.CategoryFragmentViewModel


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryFragmentViewModel


    private val categoryAdapter = CategoryAdapter(object : OnItemViewClickListener {
        override fun onItemClick(movie: OriginalSourcePreview) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(
                    MovieDetailsFragment.BUNDLE_EXTRA,
                    movie
                )
                manager.beginTransaction()
                    .add(
                        R.id.container,
                        MovieDetailsFragment.newInstance(bundle)
                    )
                    .addToBackStack("")
                    .commit()
            }
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
        binding.rvMoviesList.layoutManager = LinearLayoutManager(context)
        binding.rvMoviesList.adapter = categoryAdapter
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