package com.deloitte.deloittetask.ui.user_screen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.deloitte.deloittetask.BR
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.generic_adapter.GenericAdapter
import com.deloitte.deloittetask.databinding.FragmentDashboardBinding
import com.deloitte.deloittetask.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var adapter: GenericAdapter
    private lateinit var binding: FragmentDashboardBinding
    private val sharedViewModel by activityViewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(BR.viewModel,sharedViewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        setListeners()

        sharedViewModel.getMostPopularArticles()

    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            sharedViewModel.getMostPopularArticles()
        }
    }

    companion object {
        const val TAG = "DashBoard"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DashboardFragment().apply {

            }
    }
}