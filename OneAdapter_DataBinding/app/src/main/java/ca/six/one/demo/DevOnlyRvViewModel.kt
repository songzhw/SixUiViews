package ca.six.one.demo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class DevOnlyRvViewModel : ViewModel() {
    var data = MutableLiveData<List<DevOnlyUser>>()

    fun init() {
        Thread(object : Runnable {
            override fun run() {
                println("szw before sleep")
                Thread.sleep(2000)
                println("szw after sleep")

                val list = ArrayList<DevOnlyUser>()
                (1..15).forEach { list.add(DevOnlyUser("item$it", it)) }
                data.postValue(list)
            }
        }).start()
    }
}