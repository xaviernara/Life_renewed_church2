package com.example.life_renewed.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.life_renewed.R
import com.example.life_renewed.databinding.FragmentLinksBinding
import com.example.life_renewed.viewmodel.LinksUiState
import com.example.life_renewed.viewmodel.LinksViewModel

class LinksFragment : Fragment() {

    companion object {
        fun newInstance() = LinksFragment()
    }

    // 1. Nullable private variable to store the binding
    private var _binding: FragmentLinksBinding? = null

    // 2. Non-nullable property for easy access (valid only between onCreateView and onDestroyView)
    private val binding get() = _binding!!
    private val adapter = LinksAdapter()

    private val viewModel: LinksViewModel by viewModels()

    val uiState = viewModel.uiState.value


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 3. Inflate the layout and initialize the binding
        _binding = FragmentLinksBinding.inflate(inflater, container, false)
        when(uiState){
            is LinksUiState.Loading -> {
                binding.spinner.visibility = View.VISIBLE
            }
            is LinksUiState.Success -> {
                binding.spinner.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.navButton.visibility = View.VISIBLE

            }
            is LinksUiState.Error -> {
            }

            else -> {}
        }

        viewModel.links.observe(viewLifecycleOwner) { links ->
            adapter.submitLinks(links)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 4. Use the binding to access views and set their properties inding.textView.text = getString(R.string.links)
        // Setup RecyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.navButton.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.container, LinksDetailsFragment())
//                .addToBackStack(null).commit()
//
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 5. CRITICAL: Clear the binding to prevent memory leaks
        _binding = null
    }
}