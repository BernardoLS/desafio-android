package com.picpay.desafio.android.user.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.core.extensions.gone
import com.picpay.desafio.android.core.extensions.visible
import com.picpay.desafio.android.user.databinding.FragmentUserListBinding
import com.picpay.desafio.android.user.presentation.view.adpter.UserListAdapter
import kotlinx.coroutines.flow.onEach

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding = _binding!!

    private val userListAdapter: UserListAdapter by lazy { UserListAdapter() }

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpStateObserver()

    }

    private fun setUpAdapter() {
        binding.recyclerUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerUserList.adapter = userListAdapter
    }

    private fun setUpStateObserver() {
        viewModel.usersState.onEach { state ->
            when (state) {
                is UserState.Loading, UserState.Empty -> binding.userListProgressBar.visible()
                is UserState.Success -> {
                    state.users.let(userListAdapter::updateUserList)
                    binding.userListProgressBar.gone()
                }
                is UserState.Error -> {
                    binding.userListProgressBar.gone()
                    showError(state.message)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }
}