package com.kldmohammed.yassir.movapp.features.movies.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kldmohammed.androidtechtask.common.UiState
import com.kldmohammed.yassir.movapp.common.extensions.errorSnackBar
import com.kldmohammed.yassir.movapp.common.extensions.gone
import com.kldmohammed.yassir.movapp.common.extensions.show
import com.kldmohammed.yassir.movapp.databinding.FragmentMoviesBinding
import com.kldmohammed.yassir.movapp.features.movies.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {
    
    private val viewModel by viewModel<MoviesViewModel>()
    
    
    private var _binding: FragmentMoviesBinding? = null
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val moviesAdapter = MoviesListAdapter()
        
        binding.movieRecyclerView.apply {
            hasFixedSize()
            adapter = moviesAdapter
        }
        
        moviesAdapter.onMovieClicked = { movie: Movie ->
            val directions = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
                movie.id)
            findNavController().navigate(directions)
        }
        
        viewModel.uiSate.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    binding.progressBar.gone()
                    moviesAdapter.submitList(it.data)
                }
                is UiState.Loading -> {
                    binding.progressBar.show()
                }
                is UiState.Error -> {
                    binding.progressBar.gone()
                    binding.root.errorSnackBar(it.throwable.message.toString())
                }
                else -> {}
            }
            
        }
    }
}