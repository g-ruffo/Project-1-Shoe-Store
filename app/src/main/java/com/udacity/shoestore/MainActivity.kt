package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var inflater: NavInflater
    private lateinit var graph: NavGraph
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())

        navController = findNavController(R.id.myNavHostFragment)

        navHostFragment = (supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment)
        inflater = navHostFragment.navController.navInflater
        graph = inflater.inflate(R.navigation.navigation)

        //Themes windowActionBar = false, connect layouts toolbar before setting up the NavController
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        graph.startDestination = R.id.loginFragment
        navHostFragment.navController.graph = graph
        navigationAppBarConfig()


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> supportActionBar?.hide()
                R.id.onBoardingWelcomeFragment -> supportActionBar?.hide()
                R.id.onBoardInstructionFragment -> supportActionBar?.hide()
                R.id.storeListFragment -> supportActionBar?.show()
                R.id.newJetTagFragment -> supportActionBar?.show()

            }

        }

        Timber.i("Has logged in = ${sharedViewModel.loggedIn.value}")
        Timber.i("Has registered = ${sharedViewModel.registerLogin.value}")

        sharedViewModel.mediatorLiveData.observe(this, Observer { loginStatus ->
            val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
            Timber.i("Total backstack = $backStackEntryCount")

            if (loginStatus.first == true && loginStatus.second == false || loginStatus.first == true && loginStatus.second == true){
                graph.startDestination = R.id.storeListFragment

            } else if (loginStatus.first == false && loginStatus.second == true){
                graph.startDestination = R.id.onBoardingWelcomeFragment

            }else if (loginStatus.first == false && loginStatus.second == false) {
                graph.startDestination = R.id.loginFragment
        }

            navHostFragment.navController.graph = graph
            navigationAppBarConfig()

            Timber.i("MediatorLiveData = ${sharedViewModel.mediatorLiveData.value} ")

        })

    }

    override fun onBackPressed() {
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
        Timber.i("Total backstack = $backStackEntryCount")
        if (backStackEntryCount == 0) onStop()
        super.onBackPressed()
    }

    private fun navigationAppBarConfig(){
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


}
