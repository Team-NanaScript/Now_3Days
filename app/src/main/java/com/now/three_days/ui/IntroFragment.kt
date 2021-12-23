package com.now.three_days.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    private var mainActivity: MainActivity? = null
    private val TAG = "intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        val mainAct = activity as MainActivity
//        mainAct.setBottomNav(false)

        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        mainActivity?.setBottomNav(false)
        Handler(Looper.getMainLooper()).postDelayed(hideSystemUI, 100)
        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(R.id.action_navigation_intro_to_navigation_home)
        }, 3500)

//        mainActivity.setBottomNav(false)
//        Handler(Looper.getMainLooper()).postDelayed({
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                requireActivity().window.setDecorFitsSystemWindows(false)
//
//                val controller = requireActivity().window.insetsController
//
//                controller?.systemBarsBehavior =
//                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//
//
//                controller?.show(WindowInsets.Type.navigationBars())
//                controller?.show(WindowInsets.Type.captionBar())
//                controller?.show(WindowInsets.Type.statusBars())
//                controller?.show(WindowInsets.Type.systemBars())
//            }
//            findNavController().navigate(R.id.action_navigation_intro_to_navigation_home)
////
//        }, 3500)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        ObjectAnimator.ofFloat(this.binding.introImage, View.ALPHA,  0f,1f).apply {
            Log.d("ANIM",this.toString())
            duration = 3000
            start()
        }

        ObjectAnimator.ofFloat(this.binding.introImage, View.ROTATION,  -180f,0f).apply {
            Log.d("ANIM",this.toString())
            duration = 3000
            start()
        }
        */
//        View.SCALE_X, 0.5f,5f,1f

        ObjectAnimator.ofFloat(this.binding.introImage, View.ALPHA, 0f, 1f).apply {
            Log.d("ANIM", this.toString())
            duration = 3000
            start()
        }

        ObjectAnimator.ofFloat(this.binding.introImage, View.TRANSLATION_Y, 0f, -100f, 0f).apply {
            Log.d("ANIM", this.toString())
            duration = 3000
            floatArrayOf()
            start()
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("RESUME", "RESUME")

//        Handler(Looper.getMainLooper()).removeCallbacks(hideSystemUI)
//        Handler(Looper.getMainLooper()).postDelayed(hideSystemUI, 100)

    }

    override fun onDestroyView() {
        super.onDestroyView()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            requireActivity().window.setDecorFitsSystemWindows(false)
//
//            val controller = requireActivity().window.insetsController
//
//            controller?.systemBarsBehavior =
//                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//
//
//            controller?.show(WindowInsets.Type.navigationBars())
//            controller?.show(WindowInsets.Type.captionBar())
//            controller?.show(WindowInsets.Type.statusBars())
//            controller?.show(WindowInsets.Type.systemBars())
//
//
//        }
//

        Handler(Looper.getMainLooper()).postDelayed(showSystemUI, 10)

    }


    private val hideSystemUI = Runnable {
        // Bottom Nav 감추기
        mainActivity?.setBottomNav(false)

        // 화면구현 default FLAG 를 무시하고 코드로 적용하기 위해 false
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            Log.d("Hide", "HideUI")
            val controller = requireActivity().window.insetsController

            // 키보드 감추기
            controller?.hide(WindowInsets.Type.ime())

            // 전체화면 설정
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // 디바이스 하단 시스템 메뉴 제거
            controller?.hide(WindowInsets.Type.systemBars())

            /**
             * 상단 상태바(시계)를 감추고 화면영역을 넓게 보이기
             */
            // 상단 상태바(시계등) 감추기
//            controller?.hide(WindowInsets.Type.statusBars())

            /**
             * 화면 늘리기
             * theme 에 <item name="android:windowLayoutInDisplayCutoutMode">shortEdges</item> 부분을 추가
             * minSDK 는 27 이상으로 설정
             */
            controller?.hide(WindowInsets.Type.displayCutout())

//            controller?.hide(WindowInsets.Type.navigationBars())
            controller?.hide(WindowInsets.Type.captionBar())

        } else {
            val flags =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            activity?.window?.decorView?.systemUiVisibility = flags

        }

        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    private val showSystemUI = Runnable {
        // Bottom Nav 감추기
//        mainActivity?.setBottomNav(true)

        // 화면구현 default FLAG 를 무시하고 코드로 적용하기 위해 false
        WindowCompat.setDecorFitsSystemWindows(mainActivity?.window!!, true)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            Log.d("Hide", "HideUI")
            val controller = mainActivity?.window?.insetsController

            // 디바이스 하단 시스템 메뉴 제거
            controller?.show(WindowInsets.Type.systemBars())

            /**
             * 상단 상태바(시계)를 감추고 화면영역을 넓게 보이기
             */
            // 상단 상태바(시계등) 감추기
            controller?.show(WindowInsets.Type.statusBars())

            /**
             * 화면 늘리기
             * theme 에 <item name="android:windowLayoutInDisplayCutoutMode">shortEdges</item> 부분을 추가
             * minSDK 는 27 이상으로 설정
             */
            controller?.show(WindowInsets.Type.displayCutout())

//            controller?.hide(WindowInsets.Type.navigationBars())
            controller?.show(WindowInsets.Type.captionBar())

        } else {
            val flags =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            mainActivity?.window?.decorView?.systemUiVisibility = flags

        }
        (mainActivity as? AppCompatActivity)?.supportActionBar?.show()
    }


}