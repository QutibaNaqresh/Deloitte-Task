package com.deloitte.deloittetask.ui.user_screen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.generic_adapter.GenericAdapter
import com.deloitte.deloittetask.databinding.FragmentDashboardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private lateinit var adapter:GenericAdapter
    private lateinit var binding: FragmentDashboardBinding
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
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_dashboard, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GenericAdapter()
        adapter.addElement(Any())
        adapter.addElement(Any())
        adapter.addElement(Any())
        adapter.addElement(Any())
        adapter.addElement(Any())
        binding.recyclerView.adapter = adapter
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