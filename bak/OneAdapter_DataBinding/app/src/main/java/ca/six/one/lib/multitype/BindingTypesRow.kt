package ca.six.one.lib.multitype

open class BindingTypesRow(
        val layoutResId : Int,
        vararg var details : ID_Model){
}

// make data as a **Any** type, it's because different bindeingId points to different type. So <T> is not a good option here.
class ID_Model(val bindingId : Int, var data : Any){}