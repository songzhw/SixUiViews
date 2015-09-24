# SixUiViews
Custom Views from my practise or job

## 1. MaxHeight ViewGroup
ViewGroup in Android has "android:minHeight" and "android:minWidth", and has no "android:maxHeight" attribute.<br>
However, sometimes, we do need the ViewGroup whose height is "wrap_content", but has a limit of max height, like 300dp.<p>

### 1.1 usuage
(layout-xml)
```xml
<cn.song.view.view.MaxHeightRelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="300dp"
    android:layout_height="wrap_content"
    app:max_height="360dp"
    >
    ...
</cn.song.view.view.MaxHeightRelativeLayout>
```

<p> that's it!

## 2. TabsView


## 3. LoginNameEditText



## 4. Progress and Done View

## 5. RichTextView
* (1). Syntax : the markdown syntax (, which can be cross-platform). 
* (2). Parse  : Do not use HtmpParse.jar or MardownParse.jar. I want to do it myself.   "markdown --->  SpannableString"
* (3). View   : Do not use WebView, which may have some problems. I want to use TextView. <p>

And I think, too much rich element is actually not that much needed. <br/>
```Bold, italic, background, textColor, textSize, strikeThroughLine, underline, and phone call``` is my first target.





