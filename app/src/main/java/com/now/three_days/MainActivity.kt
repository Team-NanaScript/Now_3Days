package com.now.three_days

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.now.three_days.databinding.MainActivityBinding
import com.now.three_days.service.UserFile
import com.now.three_days.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.BottomNav {

    private lateinit var binding: MainActivityBinding

    private lateinit var userFile: UserFile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        */

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if(actionBar != null) {
            actionBar?.setDisplayShowHomeEnabled(true)
        }

        navView.setupWithNavController(navController)

        if (initUser())
            navController.navigate(R.id.navigation_home)
        else
            navController.navigate(R.id.action_global_navigation_login)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_nav_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val setting_item:Int = item.itemId
        if(setting_item == R.id.logout) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item);
    }

    override fun setBottomNav(status: Boolean) {
        binding.navView.visibility = if(status) View.VISIBLE else View.GONE
    }

    fun getFile():UserFile {
        return userFile
    }

    private fun initUser():Boolean {
        userFile = UserFile(filesDir.path)
        return userFile.userLog()
    }


}