package nivida.com.mformilk.SubscriptionTab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nivida.com.mformilk.R;


public class SubAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageModel> imageModelArrayList;


    public SubAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {

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
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SubAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new SubAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.subitem, null, true);
            holder.tvid= (TextView) convertView.findViewById(R.id.tvid);
            holder.tvdate= (TextView) convertView.findViewById(R.id.tvdate);
            holder.tvamount= (TextView) convertView.findViewById(R.id.tvamount);
            holder.tvstatus= (TextView) convertView.findViewById(R.id.tvstatus);
            holder.img_option= (ImageView) convertView.findViewById(R.id.img_options);
            holder.layout_cardView= (CardView) convertView.findViewById(R.id.layout_cardView);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (SubAdapter.ViewHolder)convertView.getTag();
        }

        String orderAmount= context.getString(R.string.Rs);
        holder.tvid.setText(String.valueOf(imageModelArrayList.get(position).getItemId()));
        holder.tvdate.setText(imageModelArrayList.get(position).getDate());
        holder.tvstatus.setText(imageModelArrayList.get(position).getStatus());
        holder.tvamount.setText(orderAmount+""+imageModelArrayList.get(position).getAmount());

        holder.layout_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(context,SubscriptionDetails.class);
                i.putExtra("id",String.valueOf(imageModelArrayList.get(position).getItemId()));
                i.putExtra("date",imageModelArrayList.get(position).getDate());
                i.putExtra("status",imageModelArrayList.get(position).getStatus());
                context.startActivity(i);
              //  ((Activity)context).finish();
                ((Activity)context).overridePendingTransition(R.anim.from_left, R.anim.from_right);
            }
        });


        return convertView;
    }

    private class ViewHolder {

        protected TextView tvid,tvdate,tvstatus,tvamount;
       CardView layout_cardView;
        private ImageView img_option;

    }

}