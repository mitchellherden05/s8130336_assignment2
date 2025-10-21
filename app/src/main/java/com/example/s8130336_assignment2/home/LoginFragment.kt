package com.example.s8130336_assignment2.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s8130336_assignment2.R
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    // Get a reference to the ViewModel using the by viewModels() delegate.
    // Hilt will automatically provide the correct instance.
    private val viewModel: LoginViewModel by viewModels()

    // Declare views. These will be initialized in onViewCreated.
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment. This is the only thing needed in onCreateView.
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views using findViewById. It's crucial to do this in onViewCreated.
        usernameEditText = view.findViewById(R.id.usernameEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        loginButton = view.findViewById(R.id.loginButton)
        progressBar = view.findViewById(R.id.progressBar)

        // Set up the click listener for the login button.
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.performLogin(username, password)
            } else {
                Toast.makeText(requireContext(), "Username and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe the loginResult LiveData from the ViewModel.
        observeLoginResult()
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoginResult.Loading -> {
                    // Show the progress bar and disable the button
                    progressBar.isVisible = true
                    loginButton.isEnabled = false
                }
                is LoginResult.Success -> {
                    // Hide the progress bar and re-enable the button
                    progressBar.isVisible = false
                    loginButton.isEnabled = true

                    // Show a success message and navigate
                    Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                }
                is LoginResult.Error -> {
                    // Hide the progress bar and re-enable the button
                    progressBar.isVisible = false
                    loginButton.isEnabled = true

                    // Show an error message
                    Toast.makeText(requireContext(), "Login Failed: ${result.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
