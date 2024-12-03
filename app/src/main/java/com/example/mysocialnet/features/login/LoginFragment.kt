package com.example.mysocialnet.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mysocialnet.MySocialNetApp
import com.example.mysocialnet.R
import com.example.mysocialnet.databinding.FragmentLoginBinding
import com.example.mysocialnet.utils.ValidationHelper
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelProvider.Factory
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MySocialNetApp).dispatchingAndroidInjector.inject(this)
        loginViewModel =
            ViewModelProvider(this, viewModelInjectionFactory)[LoginViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            binding.emailInputLayout.error = ValidationHelper.validateEmail(email)
            binding.passwordInputLayout.error = ValidationHelper.validatePassword(password)

            if (loginViewModel.validateInput(email, password)) {
                loginViewModel.loginUser()
                if (loginViewModel.isAuthenticated.value == true) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}