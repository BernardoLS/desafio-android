package com.picpay.desafio.android.user.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.core.extensions.gone
import com.picpay.desafio.android.core.extensions.visible
import com.picpay.desafio.android.user.databinding.FragmentUserListBinding
import com.picpay.desafio.android.user.presentation.intents.UserListIntents
import com.picpay.desafio.android.user.presentation.model.UserModel
import com.picpay.desafio.android.user.presentation.view.adapter.UserListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private val userListAdapter: UserListAdapter by lazy { UserListAdapter() }

    private val viewModel: UserViewModel by viewModel()

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
        setUpRefresh()

        viewModel.sendIntent(UserListIntents.LoadUsers)
    }

    private fun setUpAdapter() {
        binding.recyclerUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerUserList.adapter = userListAdapter
    }

    private fun setUpStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersState.collect { state ->
                when (state) {
                    is UserState.Loading, UserState.Empty -> binding.userListProgressBar.visible()

                    is UserState.Success -> bindSuccessState(state.users)

                    is UserState.Error -> bindErrorState(state.message)

                }
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindSuccessState(users: List<UserModel>) {
        userListAdapter.updateUserList(users)
        binding.recyclerUserList.visible()
        binding.userListProgressBar.gone()
    }

    private fun bindErrorState(error: String) {
        binding.userListProgressBar.gone()
        binding.recyclerUserList.gone()
        binding.tvErrorMessage.text = error

        binding.constraintErrorState.visible()

        binding.btnPill.setOnClickListener {
            viewModel.sendIntent(UserListIntents.LoadUsers)
        }
    }

    private fun setUpRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.sendIntent(UserListIntents.LoadUsers)
        }
    }

    companion object {
        fun newInstance() = UserListFragment()
    }
}