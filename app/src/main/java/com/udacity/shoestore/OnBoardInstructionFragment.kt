package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentOnBoardInstructionBinding
import java.util.zip.Inflater

class OnBoardInstructionFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardInstructionBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_board_instruction, container, false)

        // Bind the SharedViewModel to the View Layout
        binding.sharedViewModel = sharedViewModel

        binding.informationTitleTextView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left_view))
        binding.instructionsBlockTextView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right_view))
        binding.instructionsNextButton.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_up_view))

        sharedViewModel.mediatorLiveData.observe(viewLifecycleOwner, Observer { loggedIn ->
            if (loggedIn.first == true){
                findNavController().navigate(R.id.storeListFragment)
            }
        })


        return binding.root
    }


}