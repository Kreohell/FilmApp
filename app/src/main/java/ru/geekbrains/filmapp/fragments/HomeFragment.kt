package ru.geekbrains.filmapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.adapters.HomeMovieAdapter
import ru.geekbrains.filmapp.data.WantMovie
import ru.geekbrains.filmapp.data.WatchedMovie
import ru.geekbrains.filmapp.databinding.FragmentHomeBinding
import ru.geekbrains.filmapp.models.SimpleMovie
import ru.geekbrains.filmapp.utils.Constants.Companion.APP_PREFERENCE
import ru.geekbrains.filmapp.utils.Constants.Companion.APP_PREFERENCE_ADULT_CONTENT
import ru.geekbrains.filmapp.utils.convertWantToMovie
import ru.geekbrains.filmapp.utils.convertWatchedToMovie
import ru.geekbrains.filmapp.viewmodels.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private val homeFragmentViewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    private var listLayoutManager: GridLayoutManager? = null
    private lateinit var wantMovies: List<WantMovie>
    private lateinit var watchedMovies: List<WatchedMovie>

    private lateinit var homeMovieAdapter: HomeMovieAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeBottomSheet: View
    private lateinit var homeBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mSettings: SharedPreferences

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(homeFragmentViewModel)
        doInitialization(view)
        setupBottomSheets()
        setupRecyclerView()
        setupTouchHelper()
        setupListeners()
        startObserve()
    }

    private fun doInitialization(view: View) {
        homeBottomSheet = view.findViewById(R.id.profileBottomSheet)
        homeBottomSheetBehavior = BottomSheetBehavior.from(homeBottomSheet)
        mSettings = requireActivity().getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE)

        binding.profileBottomSheet.switchAdultContent.apply {
            if (mSettings.getBoolean(APP_PREFERENCE_ADULT_CONTENT, true)) isChecked = true
        }
    }

    private fun setupBottomSheets() {
        binding.btnProfileImage.setOnClickListener {
            homeBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.profileBottomSheet.btnSaveSettings.setOnClickListener {
            saveAdultContentSetting()
            homeBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            Toast.makeText(context, "Settings were saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        listLayoutManager = GridLayoutManager(context, 1)
        homeMovieAdapter = HomeMovieAdapter(listLayoutManager)
        binding.moviesList.apply {
            layoutManager = listLayoutManager
            adapter = homeMovieAdapter
        }
    }

    private fun setupTouchHelper() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewHolder.adapterPosition.apply {
                    homeFragmentViewModel.deleteMovie(homeMovieAdapter.getItemItemAt(this))
                    homeMovieAdapter.deleteItem(this)
                }
            }

            override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                )
                RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                ).addBackgroundColor(
                        ContextCompat.getColor(
                                requireContext(),
                                R.color.deleteBackground
                        )
                ).addSwipeLeftLabel(getString(R.string.delete))
                        .setSwipeLeftLabelColor(ContextCompat.getColor(requireContext(), R.color.black))
                        .setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
                        .create()
                        .decorate()
            }
        }).attachToRecyclerView(binding.moviesList)
    }

    private fun setupListeners() {
        binding.btnChangeRecyclerViewLayout.setOnClickListener {
            listLayoutManager?.let { manager ->
                when (manager.spanCount) {
                    1 -> {
                        manager.spanCount = 2
                        (it as ImageView).setImageResource(R.drawable.ic_list_medium)
                    }
                    2 -> {
                        manager.spanCount = 3
                        (it as ImageView).setImageResource(R.drawable.ic_list_big)
                    }
                    else -> {
                        manager.spanCount = 1
                        (it as ImageView).setImageResource(R.drawable.ic_list)
                    }
                }
                homeMovieAdapter
                        .notifyItemRangeChanged(0,homeMovieAdapter.itemCount)
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> selectMovies(convertWantToMovie(wantMovies))
                    1 -> selectMovies(convertWatchedToMovie(watchedMovies))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun startObserve() {
        homeFragmentViewModel.readWantMovies.observe(viewLifecycleOwner, {
            wantMovies = it
            selectMovies(convertWantToMovie(it))
        })

        homeFragmentViewModel.readWatchMovies.observe(viewLifecycleOwner, {
            homeMovieAdapter.apply {
                watchedMovies = it
            }
        })
    }

    private fun selectMovies(moviesList: List<SimpleMovie>) {
        homeMovieAdapter.apply {
            clearMovies()
            addMovies(movies = moviesList)
            notifyDataSetChanged()
        }
        binding.tvMoviesCount.text = moviesList.count().toString()
    }

    private fun saveAdultContentSetting() {
        mSettings.edit().apply {
            putBoolean(
                    APP_PREFERENCE_ADULT_CONTENT,
                    binding.profileBottomSheet.switchAdultContent.isChecked
            )
            apply()
        }
    }
}