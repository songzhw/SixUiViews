package cn.six.open.view.rv.OneBindingAdapter.demo;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by songzhw on 2017-02-21
 */

public class TmpItem extends BaseObservable {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();

    public TmpItem(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }
}
