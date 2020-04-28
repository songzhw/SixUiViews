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
It will make a transpanrent png picture to show different colors programmatically, which is perfectly fit for your app/sdk if your app/sdk needs to support different brand, and each brand has their own primary color.


![](/pic/ColorMask.gif)


## 6. Custom ActionSheet View

Reference : https://github.com/baoyongzhang/android-ActionSheet

Since baoyongzhang's ActionSheet is restrict to a specific view, and I have to custom the view in ActionSheet.
So I did some work to meet my need.

![](/pic/CustomActionSheet.gif)

**2017.08.10** : update this view with a SpringAnimation. Now it support two animations :
* show with alpha animation
* show with spring animation


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

## 16. TutorialView

It is used to highlight some new element in some of our new functionality. You can highlight some specfic views and add a description to describe what's new in it. 
(p.s. click this mask view, it will go disappear automatically)
<img src="./pic/TutorialView.png" width="600" height="1000" align=center/>

## 8. Swipe-Delete EditText
Swiping in the EditText will delete all the text in this EditText.

## 9. AnimCheckBox

This is a combination of two View : CrossCheckBox, TickCheckBox.

![](/pic/AnimCheckBox.gif)

## 10. OneAdapter
You do not need to write a bunch of specific Adapter for RecyclerView anymore. Now you have OneAdapter. What you need to do is just like this:

```java
OneAdapter<String> adapter = new OneAdapter<String>(this, R.layout.item_rv_simple) {
  @Override
  protected void apply(RvViewHolder vh, String s, int position) {
      TextView tv = vh.getView(R.id.tv_rv_simple_item);
      tv.setText("Position "+position+" : "+s);
      if(position % 2 == 1){
          tv.setBackgroundColor(0xffC7EDCC);
      } else {
          tv.setBackgroundColor(0xffffffff);
      }
  }
};
adapter.data = data;
rv.setAdapter(adapter);
```


## 11. OneBindingAdapter: OneAdapter for data-binding
For data-binding, you obivous need a different version of OneAdapter. Because now you do not need to use apply() method. You declaim a data in item's layout xml, hence the data-binding framework will do this job for you. 

Here is how you can use OneBindingAdapter:

```java
    OneBindingAdapter<TmpItem> adapter = new OneBindingAdapter<>(this, R.layout.item_rv, BR.item, data);
    RecyclerView rv = binding.rvBindingDemo;
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
```

## 12. MultiAdapter
OneAdapter only works for the Adapter with one ItemViewType.
`MultiAdapter` is a general adapter for RecyclerView with multiple different ItemViewTypes.

How to use it?
: You have to define different type support class first, then pass these types to MultiAdater.

For example, I want to show a RecyclerView with a title and content. Aka, I have two ItemViewTypes.

```kotlin
        val data = arrayListOf(
                Title("001"), Content("1A"), Content("1B"), Content("1C"),
                Title("002"), Content("2A"), Content("2B"), Content("2C"), Content("2D")
        )
        val typeTitle = object : IRvType {
            override fun getLayoutResId(): Int = R.layout.item_rv_title
            override fun render(vh: RvViewHolder, datum: Any, position: Int) {
                vh.setText(R.id.tvRvTitle, (datum as Title).title)
            }
        }
        val typeContent = object : IRvType {
            override fun getLayoutResId(): Int = R.layout.item_rv_content
            override fun render(vh: RvViewHolder, datum: Any, position: Int) {
                vh.setText(R.id.tvRvContent, (datum as Content).name)
            }
        }
        val types = mapOf(Title::class.java to typeTitle, Content::class.java to typeContent)

        rvRefresh.setLayoutManager(LinearLayoutManager(this))
        val adapter = MultipleAdapter(data, types)
        rvRefresh.adapter = adapter
```

## 13. StickyColumnTableView
A tableView whose first column is stikcy at the left. 

![](/pic/StickyColumnTableView.gif)


## 14. ExpandableLayout
![](/pic/expandable_layout.gif)


## 15. RecyclerView's SwipeMenuLayout
![](/pic/rv_swipe_menu.gif)

To use it, just wrap it as the root layout of your RecyclerView Item.
It should has two, and only two, children. One is the content, the another is the menu.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout >

    <cn.six.open.view.rv.SideMenu.SwipeMenuLayout >
        <LinearLayout android:id="@+id/llayContent">
            <ImageView  />
            <TextView/>
        </LinearLayout>

        <LinearLayout android:id="@+id/llayMenu">
            <TextView/>
        </LinearLayout>
    </cn.six.open.view.rv.SideMenu.SwipeMenuLayout>

</LinearLayout>
```


### 15.1 Problems with the RecyclerView
If you open the menu of item01, and scroll RecyclerView vertically, you may find item10's menu is open as well.

To fix this, you should add a if block to close the menu every time you show a item .

This if block should be added to your Adapter.onBindViewHolder(), or OneAdapter.apply()

```java
        adapter = new OneAdapter<String>(R.layout.item_swipe_menu_demo, data) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {
                // IMPORTANT: othewise, the recycled item will be a problem when this item is open already
                SwipeMenuLayout swipeMenuLayout = vh. getView(R.id.swipeMenuLayout);
                if(swipeMenuLayout.isOpen){
                    swipeMenuLayout.close();
                }

                vh.setSrc(R.id.ivRvItemSwipe, R.drawable.ic_launcher);
                vh.setText(R.id.tvRvItemSwipe, value);
            }
        };
```




## Reference
1. https://github.com/baoyongzhang/android-ActionSheet   ActionSheet for Android
2. http://blog.csdn.net/qq_16064871/article/details/50224635   Bounced ScrollView
3. https://github.com/Doudada/DataBinding-Librar-RecyclerView-all-purpose-Adapter
