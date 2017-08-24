package nivida.com.mformilk.SubscriptionTab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nivida.com.mformilk.R;

public class AddSubscription extends AppCompatActivity {
    private ListView lv;
    LinearLayout linear_subscription;
    private ImageView addsub;
    private CustomAdapter customeAdapter;
    private ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.item, R.drawable.milk,
            R.drawable.cheese,R.drawable.item
            ,R.drawable.cheese,R.drawable.milk,
            R.drawable.item,R.drawable.cheese};
    private String[] myImageNameList = new String[]{"Milk", "Cheese",
            "Doughnut","Milk"
            ,"Doughnut","Cheese",
            "Lamborghini","Doughnut"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);
        lv = (ListView)findViewById(R.id.listviewsub);
        imageModelArrayList = populateList();

        customeAdapter = new CustomAdapter(getApplicationContext(),imageModelArrayList);
        lv.setAdapter(customeAdapter);
        fetchId();
        setupToolbar();

    }

    private void fetchId()
    {
        linear_subscription= (LinearLayout) findViewById(R.id.linear_continue);
        linear_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),CheckOut.class);
                startActivity(i);
                overridePendingTransition(R.anim.from_left,R.anim.from_right);
            }
        });


    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setName(myImageNameList[i]);
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCenterText);
        TextView actionTitle = (TextView) findViewById(R.id.actionTitle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionTitle.setText("Add Subscription");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
//                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
//                startActivity(i);
            }
        });
    }
}
