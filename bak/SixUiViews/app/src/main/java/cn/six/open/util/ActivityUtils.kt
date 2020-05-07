package cn.six.open.util

import android.app.Activity
import android.view.View

// = = = = = = = = = = = = = Full Screen = = = = = = = = = = = = =
var EXPAND_VIEW_STABLE =
        // 让view扩展到navigation bar以下
        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                // 让view扩展到status bar以下
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                // 让在全屏与非全屏切换时, view不用resize
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE

fun Activity.exitFulLScreen(){
    window.decorView.systemUiVisibility = EXPAND_VIEW_STABLE
}

fun Activity.setFullScreen_Immersive(){
    window.decorView.systemUiVisibility = EXPAND_VIEW_STABLE or
            // 让Activity全屏显示. 其实就是隐藏状态栏 (Activity里还包括actionbar, ab也会显示的哦)
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            // 隐藏navigation bar(虚拟按键)
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE
}

fun Activity.setFullScreen_ImmersiveSticky(){
    window.decorView.systemUiVisibility = EXPAND_VIEW_STABLE or
            // 让Activity全屏显示. 其实就是隐藏状态栏 (Activity里还包括actionbar, ab也会显示的哦)
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            // 隐藏navigation bar(虚拟按键)
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}

fun Activity.setFullScreen_LeanBack(){
    window.decorView.systemUiVisibility = EXPAND_VIEW_STABLE or
            // 让Activity全屏显示. 其实就是隐藏状态栏 (Activity里还包括actionbar, ab也会显示的哦)
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            // 隐藏navigation bar(虚拟按键)
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
}

// = = = = = = = = = = = = = ** = = = = = = = = = = = = =