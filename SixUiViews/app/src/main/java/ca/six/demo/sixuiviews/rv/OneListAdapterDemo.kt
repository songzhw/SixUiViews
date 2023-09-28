package ca.six.demo.sixuiviews.rv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.demo.sixuiviews.R
import ca.six.views.rv.RvViewHolder
import ca.six.views.rv.single.OneListAdapter
import kotlinx.android.synthetic.main.activity_rv_btn.*

// 要点一: 要自己写DiffUtils.ItemCallback
// 要点二: 为了方便深拷贝, 最好写一个clone方法出来
data class Food(
    val id: Int,
    val name: String,
)  {
    companion object {
        val diff = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(old: Food, now: Food) = old.id == now.id
            override fun areContentsTheSame(old: Food, now: Food) = old == now //kotlin中用==来代替equals()

        }
    }

    fun clone() = Food(this.id, this.name)
}


class OneListAdapterDemo: AppCompatActivity(R.layout.activity_rv_btn) {
    val food = arrayListOf(
        Food(11, "壽司"), Food(12, "天ぷら"), Food(13, "すき焼き"),
        Food(14, "ラーメン"), Food(15, "カレーライス"), Food(16, "とんかつ"),
        Food(17, "そば"), Food(18, "うどん"), Food(19, "唐揚げ"),
        Food(20, "焼き鳥")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = object : OneListAdapter<Food>(R.layout.item_simple_texts, Food.diff) {
            override fun apply(holder: RvViewHolder, value: Food, position: Int) {
                holder.setText(R.id.tvTitle, value.id.toString())
                holder.setText(R.id.tvDesp, value.name)
            }
        }
        adapter.submitList(food)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)


        btn1.text = "delete "
        btn1.setOnClickListener {
            val list = food.map { it.clone() } as ArrayList<Food>
            list.removeAt(3)
            adapter.submitList(list)
        }

        btn2.text = "add"
        btn2.setOnClickListener {
            val list = food.map { it.clone() } as ArrayList<Food>
            list.add(2, Food(22, "炉辺焼き"),)
            adapter.submitList(list)
        }
    }
}