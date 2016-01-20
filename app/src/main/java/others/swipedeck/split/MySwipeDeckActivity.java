package others.swipedeck.split;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.six.open.R;
import others.swipedeck.SwipeDeckAdapter;

/**
 * Created by songzhw on 2016/1/11
 */
public class MySwipeDeckActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> data = new ArrayList<String>();
        data.add("001");data.add("002");data.add("003");data.add("004");
        SwipeDeckAdapter adapter = new SwipeDeckAdapter(data, this);
        adapter.setLayoutResId(R.layout.item_card_three);

        MySwipeDeck swipeDeck = new MySwipeDeck(this);
        swipeDeck.setAdapter(adapter);

        setContentView(swipeDeck);
    }
}
