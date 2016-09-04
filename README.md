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
Custom a Tabs View for multiple tabs. 

Three tabs:

![](/pic/2016070303.png)

Four tabs:

![](/pic/2016070304.png)


## 3. LoginNameEditText
(1). show account names you inputed before

(2). show suffix along with your input

![](/pic/LoginEditText.gif)




## 5. Color Mask
It will make a transpanrent png picture to show different colors programmatically?


![](/pic/ColorMask.gif)


## 6. Custom ActionSheet View

Reference : https://github.com/baoyongzhang/android-ActionSheet

Since baoyongzhang's ActionSheet is restrict to a specific view, and I have to custom the view in ActionSheet.
So I did some work to meet my need.

![](/pic/CustomActionSheet.gif)


# 7. Scratch Card

A scratchcard is a small card, where one or more areas contain concealed information which can be revealed by scratching off an opaque covering.
Applications include cards sold for gambling (especially lottery games and quizzes), or free-of-charge cards for quizzes, and to conceal confidential information such as PINs for telephone calling cards and other prepaid services.

![](/pic/ScratchCard.gif)


In some app, Scratch Card effect is used to conceal some lottery information.

I was write this view in 2013.11, however, it has a unpleasant problem: when you touch the screen, the path you draw is black, rather than the transparent color that I want.
I used ```paint.setColor(...)``` or something to try to fix it, but I failed.

Today, 2016.01.03, I finally fix this bug.

Now the ScratchCardView is good to go!

(More details of PorterDuff.Mode.CLEAR is MyClear01View and MyClear02View)

## 8. Swipe-Delete EditText
Swiping in the EditText will delete all the text in this EditText.

## 9. AnimCheckBox

This is a combination of two View : CrossCheckBox, TickCheckBox.

![](/pic/AnimCheckBox.gif)


## Reference
1. https://github.com/baoyongzhang/android-ActionSheet   ActionSheet for Android
2. http://blog.csdn.net/qq_16064871/article/details/50224635   Bounced ScrollView
