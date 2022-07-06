package com.example.bs23exam.feature.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crowncoder.mowall.common.other.Constant
import com.example.bs23exam.R
import com.example.bs23exam.common.models.RepositoryRequest
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import com.example.bs23exam.databinding.HomeFragmentBinding
import com.example.bs23exam.feature.adapter.RepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment), RepositoryAdapter.ClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter : RepositoryAdapter
    private var repoList = arrayListOf<RepositoryItem>()
    private lateinit var binding: HomeFragmentBinding
    private var repositoryRequest = RepositoryRequest("Android", Constant.LAST_UPDATE, "asc", 50, 1)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        initializeObservers()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.shortByRadioGroup.setOnCheckedChangeListener { radioGroup, checkedID ->
            when(checkedID){
                R.id.last_update_radioButton ->{
                    repositoryRequest.shortBy = Constant.LAST_UPDATE
                    callForData()
                }
                R.id.star_count_radioButton ->{
                    repositoryRequest.shortBy = Constant.STAR_COUNT
                    callForData()
                }
            }
        }
    }

    private fun initializeView() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        adapter = RepositoryAdapter(repoList, this)
        binding.repositoryRecyclerView.adapter = adapter
        binding.repositoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        callForData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeObservers() {
        viewModel.repositoryList.observe(viewLifecycleOwner,{
            binding.progressBar.visibility = View.GONE
            repoList.clear()
            repoList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun callForData(){
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getRepositoryListFromServer(repositoryRequest)
    }

    override fun itemClicked(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }

}