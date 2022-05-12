package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentStoreListBinding
import kotlinx.android.synthetic.main.list_item.view.*

class StoreListFragment : Fragment() {

    private lateinit var binding: FragmentStoreListBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_list, container, false)

        // Show the menu on this Fragment
        setHasOptionsMenu(true)

        // Bind the SharedViewModel to the View Layout
        binding.sharedViewModel = sharedViewModel

        addViewsToLayout()

        binding.floatingAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_storeListFragment_to_newJetTagFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginFragment -> sharedViewModel.hasLoggedOut()
        }
        Toast.makeText(context, "Has logged out ${sharedViewModel.loggedIn.value}", Toast.LENGTH_SHORT).show()
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    fun addViewsToLayout(){

        for(item in sharedViewModel.jetTagList.value.orEmpty()){
            var inflatedView = layoutInflater.inflate(R.layout.list_item, null)
            inflatedView.findViewById<View>(R.id.nameTextView).nameTextView.setText(item.name)
            inflatedView.findViewById<View>(R.id.companyTextView).companyTextView.setText(item.company)
            inflatedView.findViewById<View>(R.id.sizeTextView).sizeTextView.setText(item.size.toString())
            inflatedView.findViewById<View>(R.id.descriptionTextView).descriptionTextView.setText(item.description)


            binding.listLayout.addView(inflatedView)

        }


    }



}