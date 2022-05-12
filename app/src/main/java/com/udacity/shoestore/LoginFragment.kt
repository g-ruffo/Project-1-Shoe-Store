package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Bind the SharedViewModel to the View Layout
        binding.sharedViewModel = sharedViewModel


        Timber.i("LoginFragment Created")


        binding.editTextEmail.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left_view))
        binding.editTextTextPassword.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right_view))
        binding.registerButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_up_view))
        binding.loginButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_up_view))




        return binding.root

    }


}