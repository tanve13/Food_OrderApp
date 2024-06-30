package com.tanveer.assignmenttask2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.tanveer.assignmenttask2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var  binding: ActivityMainBinding? = null
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item -> navController?.navigate(R.id.itemFragment)
                R.id.bill -> navController?.navigate(R.id.billFragment)
            }
            return@setOnItemSelectedListener true
        }
        navController = findNavController(R.id.host)
        navController?.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.itemFragment -> {
                    supportActionBar?.title = resources.getString(R.string.Item_fragment)
                    binding?.bottomNav?.menu?.findItem(R.id.item)?.isChecked = true
                }

                R.id.billFragment -> {
                    supportActionBar?.title = resources.getString(R.string.bill_fragment)
                    binding?.bottomNav?.menu?.findItem(R.id.bill)?.isChecked = true
                }
            }
        }
    }
}