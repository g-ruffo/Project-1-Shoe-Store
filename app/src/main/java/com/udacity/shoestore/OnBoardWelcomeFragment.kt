package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.FragmentOnBoardingWelcomeBinding
import timber.log.Timber

class OnBoardWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingWelcomeBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_boarding_welcome, container, false)

        Timber.i("WelcomeFragment Created")


        binding.welcomeTitleTextView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right_view))
        binding.welcomeBlockTextView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left_view))
        binding.welcomeNextButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_up_view))

        // Bind the SharedViewModel to the View Layout
        binding.sharedViewModel = sharedViewModel

        binding.welcomeNextButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_onBoardingWelcomeFragment_to_onBoardInstructionFragment))



        return binding.root
    }

}