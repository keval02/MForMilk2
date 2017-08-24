package nivida.com.mformilk.SubscriptionTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nivida.com.mformilk.R;

/**
 * Created by prince on 8/18/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageModel> imageModelArrayList;


    public CustomAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {

        this.context = context;
        this.imageModelArrayList = imageModelArrayList;

    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recyclervieitem, null, true);

           holder.itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
            holder.itemName = (TextView) convertView.findViewById(R.id.itemName);
           holder.itemCategoryName = (TextView) convertView.findViewById(R.id.itemCategoryName);
           holder.imgMinus = (ImageView) convertView.findViewById(R.id.imgMinus);
           holder.imgPlus = (ImageView) convertView.findViewById(R.id.imgPlus);
           holder.itemEachAmoumt = (TextView) convertView.findViewById(R.id.txtAmountEach);
            holder.itemTotalAmount = (TextView) convertView.findViewById(R.id.txtAmount);
           holder. edt_count_txt = (EditText) convertView.findViewById(R.id.edtItemQty);
           holder.layout=(LinearLayout)convertView.findViewById(R.id.categorynamelayout);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.itemName.setText(imageModelArrayList.get(position).getName());
        holder.itemImage.setImageResource(imageModelArrayList.get(position).getImage_drawable());

        return convertView;
    }

    private class ViewHolder {

        private TextView name, itemName, itemCategoryName, itemEachAmoumt, itemTotalAmount;
        private View btnDelete;
        private View btnEdit;

        private ImageView itemImage, imgMinus, imgPlus, delete;
        private EditText edt_count_txt;
        private LinearLayout layout;

    }

}