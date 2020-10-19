package com.ceiba.ceiba.ui.posts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ceiba.ceiba.R
import com.ceiba.ceiba.databinding.PostsFragmentBinding
import com.ceiba.ceiba.models.PostBind
import com.ceiba.ceiba.ui.posts.adapters.PostsAdapter
import com.ceiba.ceiba.utility.viewModel.ViewModelFactory
import com.ceiba.ceiba.viewModels.PostsViewModel
import com.ceiba.ceiba.viewModels.UIState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PostsViewModel by viewModels { viewModelFactory }

    private lateinit var binding : PostsFragmentBinding

    private val args : PostsFragmentArgs by navArgs()

    private lateinit var postsAdapter: PostsAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.posts_fragment, container, false
        )

        binding.postsViewModel = viewModel

        binding.lifecycleOwner = this

        setAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.user?.let { user ->
            viewModel.bindInfoUser(user)
            viewModel.getPost(user.id)
        }

        viewModel.posts.observe(viewLifecycleOwner, { status ->
            when (status) {
                is UIState.Success -> {
                    if (binding.viewSwitcher.nextView.id == binding.recyclerViewPosts.id) {
                        binding.viewSwitcher.showNext()
                    }
                    postsAdapter.submitList(status.data as List<PostBind>)
                }
                is UIState.Error -> Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setAdapter() {
        postsAdapter = PostsAdapter()
        binding.recyclerViewPosts.adapter = postsAdapter
    }

}