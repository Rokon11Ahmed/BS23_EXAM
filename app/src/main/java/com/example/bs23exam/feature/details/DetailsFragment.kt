package com.example.bs23exam.feature.details

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bs23exam.R
import com.example.bs23exam.common.models.roomDB.OwnerDetails
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import com.example.bs23exam.databinding.DetailsFragmentBinding
import com.example.bs23exam.utils.DateTimeFormatterUtil
import com.example.bs23exam.utils.ImageUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()
    private var repositoryId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        initializeObservers()
    }

    private fun initializeView() {
        repositoryId = args.repoID
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private fun initializeObservers() {
        viewModel.getRepositoryDetails(repositoryId).observe(viewLifecycleOwner, {
            it.let {
                updateRepositoryUI(it)
                it.owner?.let { it1 ->
                    viewModel.getOwnerDetailsFromServer(it1)
                }
            }
        })

        viewModel.getOwnerDetails().observe(viewLifecycleOwner, {
            updateOwnerUI(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateOwnerUI(owner: OwnerDetails?) {
        owner?.let {
            if (!owner.name.isNullOrEmpty()) {
                binding.ownerNameTextView.text = "${getString(R.string.name)} : ${owner.name}"
            } else {
                binding.ownerNameTextView.text = "${getString(R.string.name)} :"
            }
            ImageUtil.loadImage(requireContext(), owner.avatar_url, binding.ownerPhoto)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateRepositoryUI(repo: RepositoryItem?) {
        repo?.let {
            if (!repo.description.isNullOrEmpty()) {
                binding.repoDetailsTextView.text =
                    "${getString(R.string.repo_description)} : ${repo.description}"
            } else {
                binding.repoDetailsTextView.text = "${getString(R.string.repo_description)} :"
            }
            binding.repoUpdateTextView.text = "${getString(R.string.last_update)} : ${
                repo.updated_at?.let { it1 ->
                    DateTimeFormatterUtil.formatDateTime(it1)
                }
            }"
        }
    }

}