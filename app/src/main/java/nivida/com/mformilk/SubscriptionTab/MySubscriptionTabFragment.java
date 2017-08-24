package nivida.com.mformilk.SubscriptionTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import nivida.com.mformilk.R;

public class MySubscriptionTabFragment extends Fragment {


    private ListView lv;
    private ImageView addsub;
    private SubAdapter subadapter;
    private ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.item, R.drawable.milk,
            R.drawable.cheese,R.drawable.item
            ,R.drawable.cheese,R.drawable.milk,
            R.drawable.item,R.drawable.cheese,R.drawable.cheese,R.drawable.milk,
            R.drawable.item,R.drawable.cheese};
    private int[] myImageidList = new int[]{123,456,789,456,542,852,
    454,454,987,154,454,787};

    private String[] myImageStatusList =new String[]{"pending","Active","Active","Active","Active","pending","pending","Active","pending",
            "pending","Active","Active"};
    private String[] myImageDateList =new String[]{"1/02/17","2/04/17","21/8/17","19/8/17","20/8/17","20/7/17","15/8/17","19/7/17","15/8/17",
            "21/8/17","1/02/17","15/8/17"};
    private String[] myImageAmountList =new String[]{"100","500","1000","600","800","3000","1000","2000","1000",
            "1500","8000","1000"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mysubscription_tab_layout,null);
        addsub= (ImageView) rootView.findViewById(R.id.addsub);
        lv = (ListView) rootView. findViewById(R.id.listView);
        imageModelArrayList = populateList();
        subadapter = new SubAdapter(getActivity(),imageModelArrayList);
        lv.setAdapter(subadapter);
        addsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AddSubscription.class);
                startActivity(i);
              // getActivity().finish();
                getActivity().overridePendingTransition(R.anim.from_left, R.anim.from_right);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {




            }
        });

        return rootView;
    }


    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i <12; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setItemId(myImageidList[i]);
            imageModel.setImage_drawable(myImageList[i]);
            imageModel.setAmount(myImageAmountList[i]);
            imageModel.setDate(myImageDateList[i]);
            imageModel.setStatus(myImageStatusList[i]);
            list.add(imageModel);
        }

        return list;

    }

}
