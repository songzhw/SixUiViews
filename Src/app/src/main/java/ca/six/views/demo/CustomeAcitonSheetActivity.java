package ca.six.views.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ca.six.views.lib.views.custom_action_sheet.CustomActionSheet;

public class CustomeAcitonSheetActivity extends Activity implements View.OnClickListener {
    private View sheetContentView;
    private CustomActionSheet actionSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionsheet_demo);

        sheetContentView = this.getLayoutInflater().inflate(R.layout.view_action_sheet_sample, null);
        sheetContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        TextView tv1 = (TextView) sheetContentView.findViewById(R.id.tvSheetSample1);
        Button btn2 = (Button) sheetContentView.findViewById(R.id.btnSheetSample2);
        tv1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public void onClickButton(View v){
        actionSheet = new CustomActionSheet(this);
//        actionSheet.showWithAlphaAnimation(sheetContentView);
        actionSheet.showWithSpringAnimation(sheetContentView);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tvSheetSample1){
            Toast.makeText(this, "First TextView", Toast.LENGTH_SHORT).show();
            actionSheet.dismiss();
        } else if(v.getId() == R.id.btnSheetSample2){
            Toast.makeText(this, "Second Button", Toast.LENGTH_SHORT).show();
            actionSheet.dismiss();
        }
    }
}


/**
Note:
    the view passed to actionSheet.show(view) should has a click listener.
    Otherwise, click that view, will also trigger the onClick() in bgView.
*/