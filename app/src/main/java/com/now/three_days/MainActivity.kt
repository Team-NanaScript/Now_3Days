package com.now.three_days

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.now.three_days.databinding.MainActivityBinding
import com.now.three_days.service.UserFile
import com.now.three_days.ui.detail.RelayDetailFragment
import com.now.three_days.ui.main.RListFragment

class MainActivity : AppCompatActivity() {

    private val rListFragment = RListFragment()
    private val detailFragment = RelayDetailFragment()

//    private lateinit var rListAdapter: RListAdapter

    private lateinit var binding: MainActivityBinding

    private lateinit var navController: NavController

    private lateinit var userFile: UserFile

//    fun changeFragment(positon : Int) {
//        when(positon) {
//            1 -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .add(R.id.rList, rListFragment)
//                    .commit()
//            }
//
//            2 -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.rList, detailFragment)
//                    .commit()
//            }
//
//        }
//    }


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
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController = findNavController(R.id.nav_host_fragment_activity_main)

//        val toolbar:Toolbar = binding.toolbar

//        if(actionBar != null) {
//            actionBar?.setDisplayShowHomeEnabled(true)
//        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navView.setupWithNavController(navController)
//        toolbar.setupWithNavController(navController)

        userFile = UserFile(filesDir.path)

        // this.hideSystemUI()
    }
//
//    fun setFragment(fragment: RListFragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//            .replace(R.id.rList,fragment)
//        transaction.commit()
//
//    }
//
//    fun setDataFragment(fragment: RListFragment, title:String) {
//        val bundle = Bundle()
//        bundle.putString("title", title)
//
//        fragment.arguments = bundle
//        setFragment(fragment)
//    }

    private fun hideSystemUI() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            // 화면구현 default FLAG 를 무시하고 코드로 적용하기 위해 false
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val controller = window.insetsController

            // Hide the keyboard (IME)
            controller?.hide(WindowInsets.Type.ime())

            // Sticky Immersive is now ...
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

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
        when (item?.itemId) {
            R.id.settingsFragment -> {
                navController.navigate(R.id.settingsFragment)
            }
            R.id.logout -> {
//                Toast.makeText(applicationContext, "로그아웃", Toast.LENGTH_SHORT).show()
                alertdialog(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun alertdialog(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("프로그램 종료")
        alertDialogBuilder.setMessage("로그아웃을 하시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("로그아웃",
                DialogInterface.OnClickListener { dialog, id ->
                    userFile.remove("memo/test")
                    val pid = Process.myPid()
                    Process.killProcess(pid)
                    finish()
                })
            .setNegativeButton("취소",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        val alertDialog = alertDialogBuilder.create()

        alertDialog.show()
    }

    fun setBottomNav(status: Boolean) {
        binding.navView.visibility = if (status) View.VISIBLE else View.GONE
//        binding.toolbar.visibility = if (status) View.VISIBLE else View.GONE
        binding.appBarLayout.visibility = if (status) View.VISIBLE else View.GONE
    }

    fun getFile(): UserFile {
        return userFile
    }

    override fun onDestroy() {
        super.onDestroy()
        userFile.remove("memo/test")
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || super.onSupportNavigateUp()
    }
}

