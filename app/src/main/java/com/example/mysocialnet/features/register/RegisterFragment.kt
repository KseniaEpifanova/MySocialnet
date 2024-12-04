package com.example.mysocialnet.features.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mysocialnet.MySocialNetApp
import com.example.mysocialnet.R
import com.example.mysocialnet.databinding.FragmentRegisterBinding
import com.example.mysocialnet.models.UserModel
import com.example.mysocialnet.utils.Errors
import javax.inject.Inject

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelInjectionFactory: ViewModelProvider.Factory
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as MySocialNetApp).dispatchingAndroidInjector.inject(this)

        registerViewModel =
            ViewModelProvider(this, viewModelInjectionFactory).get(RegisterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.registerButton.setOnClickListener {
            val model = UserModel(
                binding.nameInput.text.toString(),
                binding.emailInput.text.toString(),
                binding.ageInput.text.toString().toInt(),
                binding.passwordInput.text.toString(),
                binding.confirmPasswordInput.text.toString()
            )
            if (registerViewModel.validateFields(model)) {
                registerViewModel.registerUser(model.email, model.password)
                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            }
        }
    }

    private fun setupObservers() {
        registerViewModel.fieldErrors.observe(viewLifecycleOwner, Observer { errors ->
            displayValidationErrors(errors)
        })

        registerViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun displayValidationErrors(errors: Map<Errors, String>) {
        binding.nameInputLayout.error = null
        binding.emailInputLayout.error = null
        binding.ageInputLayout.error = null
        binding.passwordInputLayout.error = null
        binding.confirmPasswordInputLayout.error = null

        errors[Errors.NAME]?.let { binding.nameInputLayout.error = it }
        errors[Errors.EMAIL]?.let { binding.emailInputLayout.error = it }
        errors[Errors.AGE]?.let { binding.ageInputLayout.error = it }
        errors[Errors.PASSWORD]?.let { binding.passwordInputLayout.error = it }
        errors[Errors.CONFIRM_PASSWORD]?.let {
            binding.confirmPasswordInputLayout.error = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
