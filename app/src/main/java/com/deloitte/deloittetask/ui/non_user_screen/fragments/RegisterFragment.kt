package com.deloitte.deloittetask.ui.non_user_screen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.deloitte.deloittetask.BR
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.common.DateHelper
import com.deloitte.deloittetask.databinding.FragmentRegisterBinding
import com.deloitte.deloittetask.viewmodels.NonUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(){

    private lateinit var binding: FragmentRegisterBinding
    private val sharedViewModel by activityViewModels<NonUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setListeners()
    }

    private fun initialize() {
        binding.setVariable(BR.viewModel, sharedViewModel)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setListeners() {

    }

    companion object {

        const val TAG = "Register"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {

            }
    }


}