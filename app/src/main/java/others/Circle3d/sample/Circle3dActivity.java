package others.Circle3d.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;


import cn.six.open.R;
import others.Circle3d.TagCloudView;

public class Circle3dActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_d_circle);

        TagCloudView tagCloudView = (TagCloudView) findViewById(R.id.tag_cloud);
        tagCloudView.setBackgroundColor(Color.LTGRAY);

        TextTagsAdapter tagsAdapter = new TextTagsAdapter(new String[13]);
        tagCloudView.setAdapter(tagsAdapter);
    }
}
