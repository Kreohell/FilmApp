package ru.geekbrains.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.geekbrains.filmapp.R
import ru.geekbrains.filmapp.databinding.FragmentMovieDetailsBinding
import ru.geekbrains.filmapp.interactors.StringInteractorImpl
import ru.geekbrains.filmapp.viewmodels.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        movieDetailsViewModel = MovieDetailsViewModel(stringInteractor = StringInteractorImpl(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(movieDetailsViewModel)
        movieDetailsViewModel.getReceivedMovieInfo(args.moveId)
        startObserve()
    }



    private fun startObserve() {
        movieDetailsViewModel.getObservedMovie().observe(viewLifecycleOwner) {
            setTitle(title = it.title)
            setDate(date = it.release_date)
            setCountry(name = movieDetailsViewModel.getCountry(it.production_countries))
            setDescription(overview = movieDetailsViewModel.getDescription(it.overview))
            setDuration(duration = movieDetailsViewModel.getDuration(it.runtime))
            setRating(rating = it.vote_average.toString())
        }
    }

    private fun setTitle(title: String) {
        binding.tvMovieName.text = title
    }

    private fun setDate(date: String) {
        binding.tvReleaseYear.text = movieDetailsViewModel.convertDate(date)
    }

    private fun setCountry(name: String) {
        binding.tvReleaseCountry.text = name
    }

    private fun setDescription(overview: String) {
        binding.tvOverView.text = overview
    }

    private fun setDuration(duration: String) {
        binding.tvDuration.text = duration
    }

    private fun setRating(rating: String) {
        binding.tvRating.text = rating
    }

}