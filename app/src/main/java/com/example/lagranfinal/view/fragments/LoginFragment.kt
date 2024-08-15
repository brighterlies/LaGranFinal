package com.example.lagranfinal.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.lagranfinal.R
import com.example.lagranfinal.databinding.FragmentLoginBinding
import com.example.lagranfinal.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val productFragment = ProductFragment()
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnEnter.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val pass = binding.passInput.text.toString()
            loginViewModel.login(email, pass)
            if (loginViewModel.loginSuccess.value == true) {
                Toast.makeText(requireContext(), "Welcome $email", Toast.LENGTH_SHORT).show()
                setCurrentFragment(productFragment)
            } else {
                Toast.makeText(requireContext(), "Wrong Email or Pass", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun setCurrentFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}