package com.ceiba.ceiba.ui.users

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ceiba.ceiba.R
import com.ceiba.ceiba.databinding.UsersFragmentBinding
import com.ceiba.ceiba.models.UserBind
import com.ceiba.ceiba.ui.users.adapters.UsersAdapter
import com.ceiba.ceiba.utility.viewModel.ViewModelFactory
import com.ceiba.ceiba.viewModels.UIState
import com.ceiba.ceiba.viewModels.UsersViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UsersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UsersViewModel by viewModels { viewModelFactory }

    private lateinit var binding : UsersFragmentBinding

    private lateinit var usersAdapter: UsersAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(
            inflater, R.layout.users_fragment, container, false
        )

        binding.lifecycleOwner = this

        setAdapter()

        setupSearchViewUsers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()

        viewModel.users.observe(viewLifecycleOwner, { status ->
            when (status) {
                is UIState.Success -> {
                    if (binding.viewSwitcherLottie.nextView.id == binding.viewSwitcher.id) {
                        binding.viewSwitcherLottie.showNext()
                    }
                    usersAdapter.submitList(status.data as List<UserBind>)
                }
                is UIState.Error -> Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun setupSearchViewUsers() {
        binding.searchViewUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterUsers(newText)?.let { listUserBind ->
                    binding.apply {
                        if (listUserBind.count() > 0) {
                            if (viewSwitcher.nextView.id == binding.recyclerViewUsers.id) {
                                viewSwitcher.showNext()
                            }
                            usersAdapter.submitList(listUserBind)
                        } else {
                            if (viewSwitcher.nextView.id == binding.emptyView.containerEmptyView.id) {
                                viewSwitcher.showNext()
                            }
                        }
                    }
                }
                return true
            }
        })
    }

    private fun setAdapter() {
        usersAdapter = UsersAdapter { user ->
            findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToPostsFragment(user)
            )
        }
        binding.recyclerViewUsers.adapter = usersAdapter
    }

}