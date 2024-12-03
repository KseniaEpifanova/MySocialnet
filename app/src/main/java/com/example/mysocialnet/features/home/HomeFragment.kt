package com.example.mysocialnet.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnet.MySocialNetApp
import com.example.mysocialnet.R
import com.example.mysocialnet.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelProvider.Factory
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var imageListAdapter: ImageListAdapter
    private var isLoading = false  // prevent multiple requests at once
    private var currentPage = 1
    private val perPage = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MySocialNetApp).dispatchingAndroidInjector.inject(this)
        homeViewModel = ViewModelProvider(this, viewModelInjectionFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        loadMoreImages()
    }

    private fun setupRecyclerView() {
        imageListAdapter = ImageListAdapter { image ->
            //val action = HomeFragmentDirections.actionHomeFragmentToImageDetailFragment(image)
           // findNavController().navigate(action)
            //findNavController().navigate(R.id.action_homeFragment_to_loginFragment)

        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imageListAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading && totalItemCount <= lastVisibleItem + 5) {
                        loadMoreImages()
                    }
                }
            })
        }
    }

    private fun setupObservers() {
        homeViewModel.imageList.observe(viewLifecycleOwner, Observer { images ->
            imageListAdapter.submitList(images)
            isLoading = false
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            binding.errorTextView.visibility = if (errorMessage != null) View.VISIBLE else View.GONE
            binding.errorTextView.text = errorMessage
        })
    }

    private fun loadMoreImages() {
        isLoading = true
        homeViewModel.fetchImages(currentPage, perPage)
        currentPage++
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
