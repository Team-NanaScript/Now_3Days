package com.now.three_days

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
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


        this.hideSystemUI()


        if (initUser())
            navController.navigate(R.id.navigation_home)
        else
            navController.navigate(R.id.action_global_navigation_login)
    }

    private fun hideSystemUI() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            // 화면구현 default FLAG 를 무시하고 코드로 적용하기 위해 false
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val controller = window.insetsController

            // Hide the keyboard (IME)
            controller?.hide(WindowInsets.Type.ime())

            // Sticky Immersive is now ...
            controller?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // When we want to hide the system bars
            controller?.hide(WindowInsets.Type.systemBars())

            // 여러가지 항목들 감추기
            val flag = WindowInsets.Type.statusBars()
            WindowInsets.Type.navigationBars()
            WindowInsets.Type.captionBar()
            window?.insetsController?.hide(flag)

        } else {
            //noinspection
            @Suppress("DEPRECATION")
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
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
        binding.toolbar.visibility = if(status) View.VISIBLE else View.GONE
    }

    fun getFile():UserFile {
        return userFile
    }

    private fun initUser():Boolean {
        userFile = UserFile(filesDir.path)
        return userFile.userLog()
    }

    override fun onDestroy() {
        super.onDestroy()
        userFile.remove("memo/test")
    }
}