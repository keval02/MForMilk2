package nivida.com.mformilk.SubscriptionTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nivida.com.mformilk.R;


public class productadapter  extends BaseAdapter {
    Context context;
    private ArrayList<ProductDetails> productlist;


    public productadapter(Context context, ArrayList<ProductDetails> productlist) {

        this.context = context;
        this.productlist = productlist;

    }

    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getCount() {
        return productlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        productadapter.ViewHolder holder;
        if (convertView == null) {
            holder = new productadapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_itemadap, null, true);
            holder.subscriptionname = (TextView) convertView.findViewById(R.id.tv_subscription_name);
           // holder.subscriptioncode = (TextView) convertView.findViewById(R.id.tv_subscription_code);
          //  holder.subscriptionattribute = (TextView) convertView.findViewById(R.id.tv_subscription_attribute);
            holder.tv_qty = (TextView) convertView.findViewById(R.id.tv_qty);
            holder.tv_sellingprice = (TextView) convertView.findViewById(R.id.tv_subsellingprice);
            holder.tv_product_qty = (TextView) convertView.findViewById(R.id.tv_product_qty);
            holder.l_cal = (LinearLayout) convertView.findViewById(R.id.l_cal);
            holder.tv_product_total_cost= (TextView) convertView.findViewById(R.id.tv_product_total_cost);


            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (productadapter.ViewHolder) convertView.getTag();
        }

        holder.tv_product_qty .setText(productlist.get(position).getProduct_qty());
        holder.subscriptionname.setText(productlist.get(position).getSubname());
       // holder.subscriptioncode.setText("(" + productlist.get(position).getSubcode() + ")");
        holder.tv_sellingprice.setText(productlist.get(position).getSellingprice());
        holder.tv_product_qty.setText(productlist.get(position).getProduct_qty());
        holder.tv_qty.setText(productlist.get(position).getProduct_qty());
        holder.tv_product_total_cost.setText(context.getResources().getString(R.string.Rs) + "" + productlist.get(position).getTotal_cost().toString());



        return convertView;
    }

    private class ViewHolder {

        protected TextView subscriptionname, subscriptioncode, subscriptionattribute, tv_qty, tv_sellingprice, tv_product_qty,tv_product_total_cost;

        LinearLayout l_cal;

    }
}














