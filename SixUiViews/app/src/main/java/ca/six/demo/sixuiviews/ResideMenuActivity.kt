package ca.six.demo.sixuiviews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import ca.six.views.views.residemenu.ResideMenu
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_residemenu_home.*

/**
 * Created by songzhw on 2016-05-24.
 */
class ResideMenuActivity : AppCompatActivity(R.layout.activity_residemenu_home) {
    private lateinit var resideMenu: ResideMenu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = layoutInflater
        /* we will add the menuView manually, so the parent is null for now */
        @SuppressLint("InflateParams")
        val menuView = inflater.inflate(R.layout.side_rmenu_left, null)
        menuView.alpha = 0f

        resideMenu = ResideMenu(this)
        resideMenu.attach2Activity(this, menuView)
        resideMenu.setBackgroundResource(R.drawable.wallpaper04)

        menuView.findViewById<View>(R.id.tvRmenuHome).setOnClickListener {
            Toast.makeText(this, "Home: demo", Toast.LENGTH_SHORT).show()
            resideMenu.closeMenu()
        }
        menuView.findViewById<View>(R.id.tvRmenuSetting).setOnClickListener {
            Toast.makeText(this, "Setting: menu", Toast.LENGTH_SHORT).show()
            resideMenu.closeMenu()
        }
        menuView.findViewById<View>(R.id.tvRmenuCopyright).setOnClickListener {
            Toast.makeText(this, "Copyright @songzhw", Toast.LENGTH_SHORT).show()
            resideMenu.closeMenu()
        }

        btnRmenuOpen.setOnClickListener { resideMenu.openMenu() }
    }

}