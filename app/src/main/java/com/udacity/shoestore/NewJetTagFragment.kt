package com.udacity.shoestore

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentNewJetTagBinding
import com.udacity.shoestore.models.JetTag
import timber.log.Timber
import java.util.zip.Inflater

class NewJetTagFragment : Fragment() {

    private lateinit var binding: FragmentNewJetTagBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_jet_tag, container, false)



        binding.addButton.setOnClickListener {
            newJetTag()
            findNavController().navigate(NewJetTagFragmentDirections.actionNewJetTagFragmentToStoreListFragment())
        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(NewJetTagFragmentDirections.actionNewJetTagFragmentToStoreListFragment())
        }

        return binding.root
    }

    fun newJetTag(){
        var name = binding.jetTagNameEditText.text.toString().ifEmpty { "No Name" }
        var size = binding.jetTagSizeEditText.text.toString().ifEmpty { "0.0" }
        var company = binding.jetTagCompanyEditText.text.toString().ifEmpty { "No Company" }
        var description = binding.JetTagDescriptionEditText.text.toString().ifEmpty { "No Description" }

        var newJetTagItem = JetTag(name, size.toDouble(), company, description)
        sharedViewModel.addItemToList(newJetTagItem)
        Timber.i(sharedViewModel.jetTagList.value?.size.toString())
    }
}

