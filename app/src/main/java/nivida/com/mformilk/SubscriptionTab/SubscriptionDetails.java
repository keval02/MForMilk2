package nivida.com.mformilk.SubscriptionTab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nivida.com.mformilk.R;

public class SubscriptionDetails extends AppCompatActivity {


    TextView tvshippingcharge,tvsubdate,tvsubstatus,tvtotal_product,tvsubtotal,tvtotal_order,tv_id;
    private ListView sublv;
    private productadapter productadapter;
    private ArrayList<ProductDetails> productdetailslist;
    private String[] subname =new String[]{"Milk","ButterMilk","Cheese"};
  //  private String[] subcode =new String[]{"101","104"};
    private String[] subqty =new String[]{"1","1","1"};
    private String[] subsellingprice =new String[]{"20","20","20"};
    private String[] subproductqty =new String[]{"1","2","1"};
    private String[] totalcost =new String[]{"20","40","20"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_details);

        tvsubdate= (TextView) findViewById(R.id.sub_date);
        tvshippingcharge= (TextView) findViewById(R.id.tv_shippingcharge);
        tv_id= (TextView) findViewById(R.id.sub_id);
        tvsubstatus= (TextView) findViewById(R.id.sub_status);
        tvtotal_product= (TextView) findViewById(R.id.tv_total_product);
        tvsubtotal= (TextView) findViewById(R.id.tvsub_totalcharge);
        tvtotal_order= (TextView) findViewById(R.id.tv_order_total);
        sublv = (ListView)  findViewById(R.id.lv_checkout_product_list);
        productdetailslist = populateList();
        productadapter = new productadapter(getApplicationContext(),productdetailslist);
        sublv.setAdapter(productadapter);

        Intent i=getIntent();
        String id=i.getStringExtra("id");
        String date=i.getStringExtra("date");
        String status=i.getStringExtra("status");
        tv_id.setText("Subscription Id:"+" "+id);
        tvsubstatus.setText("Subscription Status:"+" "+status);
        tvsubdate.setText("Subscription Date:"+" "+date);
        tvtotal_product.setText("3");
        tvshippingcharge.setText("3");
        tvsubtotal.setText("80");
        tvtotal_order.setText("83");
        setupToolbar();

    }

    private ArrayList<ProductDetails> populateList(){

        ArrayList<ProductDetails> list = new ArrayList<>();

        for(int i = 0; i <3; i++){
            ProductDetails product = new ProductDetails();
            product.setSubname(subname[i]);
           // product.setSubcode(subcode[i]);
            product.setQty(subqty[i]);
            product.setSellingprice(subsellingprice[i]);
            product.setProduct_qty(subproductqty[i]);
            product.setTotal_cost(totalcost[i]);
            list.add(product);
        }

        return list;

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCenterText);
        TextView actionTitle = (TextView) findViewById(R.id.actionTitle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionTitle.setText("Subscription History");

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
