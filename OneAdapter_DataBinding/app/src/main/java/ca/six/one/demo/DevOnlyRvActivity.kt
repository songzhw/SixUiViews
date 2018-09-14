package ca.six.one.demo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import ca.six.one.BR
import ca.six.one.R
import ca.six.one.databinding.ActivityDevOnlyTypesBinding
import ca.six.one.lib.multitype.BindingTypesRow
import ca.six.one.lib.multitype.ID_Model

class DevOnlyRvActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityDevOnlyTypesBinding>(this, R.layout.activity_dev_only_types)
        binding.rvDemoTypes.layoutManager = LinearLayoutManager(this)
        binding.view = this
        binding.setLifecycleOwner(this)
    }

    fun rows(): List<BindingTypesRow> {
        val list = ArrayList<BindingTypesRow>()
        list.add(BindingTypesRow(R.layout.item_dev_only_one, ID_Model(BR.user, DevOnlyUser("Adam", 2000))))
        list.add(BindingTypesRow(R.layout.item_dev_only_one, ID_Model(BR.user, DevOnlyUser("Eva", 2000))))
        list.add(BindingTypesRow(R.layout.item_dev_only_person, ID_Model(BR.person, DevOnlyPerson(20, "szw", true))))
        list.add(BindingTypesRow(R.layout.item_dev_only_person, ID_Model(BR.person, DevOnlyPerson(10, "xx", false))))
        list.add(BindingTypesRow(R.layout.item_dev_only_person, ID_Model(BR.person, DevOnlyPerson(10, "dd", false))))
        list.add(BindingTypesRow(R.layout.item_dev_only_one, ID_Model(BR.user, DevOnlyUser("test", 1000))))
        list.add(BindingTypesRow(R.layout.item_dev_only_person, ID_Model(BR.person, DevOnlyPerson(20, "szw", true))))
        list.add(BindingTypesRow(R.layout.item_dev_only_person, ID_Model(BR.person, DevOnlyPerson(10, "xx", false))))
        list.add(BindingTypesRow(R.layout.item_dev_only_person, ID_Model(BR.person, DevOnlyPerson(10, "dd", false))))
        return list
    }
}
