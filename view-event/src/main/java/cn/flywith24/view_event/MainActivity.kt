package cn.flywith24.view_event

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import com.flywith24.baselib.ext.printStackTrace

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fullScreen()

        initListener()
    }

    private fun initListener() {
        findViewById<View>(R.id.vg1).setOnClickListener { printOnclick(CustomViewGroup1.NAME) }
        findViewById<View>(R.id.vg2).setOnClickListener { printOnclick(CustomViewGroup2.NAME) }
        findViewById<View>(R.id.v1).setOnClickListener { printOnclick(CustomView1.NAME) }
        findViewById<View>(R.id.v2).setOnClickListener { printOnclick(CustomView2.NAME) }
    }

    private fun fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            window.decorView.windowInsetsController?.apply {
//                hide(WindowInsets.Type.navigationBars())
                hide(WindowInsets.Type.statusBars())
            }
        else window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 一个事件序列只打印一次堆栈
        if (event.action == MotionEvent.ACTION_DOWN) printStackTrace()

        val handled = if (Config.HANDLE_ACTIVITY) true else super.onTouchEvent(event)
        printDispatchTouchEvent("Activity", "onTouchEvent", handled, event)
        return handled
    }
}