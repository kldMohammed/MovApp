package com.kldmohammed.yassir.movapp.features.movies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.kldmohammed.androidtechtask.common.UiState
import com.kldmohammed.yassir.movapp.common.extensions.errorSnackBar
import com.kldmohammed.yassir.movapp.common.extensions.format
import com.kldmohammed.yassir.movapp.common.extensions.gone
import com.kldmohammed.yassir.movapp.common.extensions.show
import com.kldmohammed.yassir.movapp.common.imagesServer
import com.kldmohammed.yassir.movapp.databinding.FragmentMovieDetailsBinding
import com.kldmohammed.yassir.movapp.features.movies.domain.model.MovieDetails
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {
    private val viewModel by viewModel<MoviesDetailsViewModel>()
    
    
    private var _binding: FragmentMovieDetailsBinding? = null
    
    private val movieId by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movieId
    }
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel.loadMovies(movieId)
        
        viewModel.uiSate.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    updateUi(uiState.data)
                    binding.detailsProgressBar.gone()
                }
                is UiState.Loading -> {
                    binding.detailsProgressBar.show()
                }
                is UiState.Error -> {
                    binding.detailsProgressBar.gone()
                    binding.root.errorSnackBar(uiState.throwable.message.toString())
                }
                else -> {}
            }
        }
    }
    
    private fun updateUi(details: MovieDetails) {
        showingViews()
        binding.movieTitle.text = details.title
        binding.movieRealeaseDate.text = details.releaseDate.format("yyyy")
        binding.movieDescription.text = details.description
        binding.voteAverage.text = details.voteAverage.toString()
        binding.ratingBar2.rating = (details.voteAverage?.toFloat()!! * 0.5).toFloat()
        
        val genresStr = details.genres?.map { it?.name }?.joinToString("/")
        
        binding.genreText.text = genresStr
        
        binding.moviePosterImage.load("$imagesServer${details.posterPath}")
        binding.backDropImageView.load("$imagesServer${details.backdropPath}")
    }
    
    private fun showingViews() {
        binding.movieDescription.show()
        binding.movieTitle.show()
        binding.moviePosterImage.show()
        binding.movieRealeaseDate.show()
        binding.backDropImageView.show()
        binding.ratingBar2.show()
        binding.voteAverage.show()
        binding.genreText.show()
    }
    
}