package ca.six.demo.sixuiviews.rv.jimu

import ca.six.demo.sixuiviews.R
import ca.six.views.rv.RvViewHolder
import ca.six.views.rv.jimu.JiMuItem

class TitleItem(val title: String) : JiMuItem(title) {
    override fun getViewType() = R.layout.item_rv_one

    override fun render(holder: RvViewHolder, position: Int) {
        holder.setText(R.id.tv_rv_item, title)
    }

}


data class Description(val desp: String, val imageResId: Int)

class DescriptionItem(val despcription: Description) : JiMuItem(despcription) {
    override fun getViewType() = R.layout.item_desp

    override fun render(holder: RvViewHolder, position: Int) {
        holder.setText(R.id.tvItem, despcription.desp)
        holder.setSrc(R.id.ivItem, despcription.imageResId)
    }
}