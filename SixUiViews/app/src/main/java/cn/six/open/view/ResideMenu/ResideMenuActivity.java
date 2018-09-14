package cn.six.open.view.ResideMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import cn.six.open.R;


/**
 * Created by songzhw on 2016-05-24.
 */
public class ResideMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu resideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residemenu_home);

        LayoutInflater inflater = getLayoutInflater();
        View menuView = inflater.inflate(R.layout.side_rmenu_left, null);
        menuView.setAlpha(0);

        resideMenu = new ResideMenu(this);
        resideMenu.attach2Activity(this, menuView);
        resideMenu.setBackgroundResource(R.drawable.wallpaper04);

        menuView.findViewById(R.id.tvRmenuHome).setOnClickListener(this);
        menuView.findViewById(R.id.tvRmenuSetting).setOnClickListener(this);
        menuView.findViewById(R.id.tvRmenuCopyright).setOnClickListener(this);
        this.findViewById(R.id.btnRmenuOpen).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRmenuOpen:
                resideMenu.openMenu();
                break;
            case R.id.tvRmenuHome:
                Toast.makeText(this, "Home: demo", Toast.LENGTH_SHORT).show();
                resideMenu.closeMenu();
                break;
            case R.id.tvRmenuSetting:
                Toast.makeText(this, "Setting: menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvRmenuCopyright:
                Toast.makeText(this, "Copyright @songzhw", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
