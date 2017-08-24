package nivida.com.mformilk.CustomCalendar;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Gravity;

import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import nivida.com.mformilk.AppPref;
import nivida.com.mformilk.R;


/**
 * Created by NWSPL-17 on 2/10/2017.
 */

public class NumberDecorator implements DayDecorator {

    HashMap<String,String> dateWithCount=new HashMap<>();
    boolean isDateSelected=false;
    String dateSelected="";
    boolean empAttendance=false;
    ArrayList<String> absentDates=new ArrayList<>();
    ArrayList<String> workingDates=new ArrayList<>();
    AppPref appPref;
    Context context;

    public NumberDecorator(HashMap<String,String> dateWithCount) {
        this.dateWithCount=dateWithCount;
        isDateSelected=false;
    }

    public NumberDecorator(HashMap<String,String> dateWithCount, String dateSelected){
        isDateSelected=true;
        this.dateWithCount=dateWithCount;
        this.dateSelected=dateSelected;
    }

    public NumberDecorator(Context context,boolean empAttendance, ArrayList<String> absentDates, ArrayList<String> workingDates){
        this.context=context;
        this.empAttendance=empAttendance;
        this.absentDates=absentDates;
        this.workingDates=workingDates;
    }

    @Override
    public void decorate(DayView cell) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());


        String cellDate=dateFormat.format(cell.getDate());


        cell.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);


        if(!empAttendance){
            if(dateSelected.equals(cellDate)){
                cell.setBackgroundColor(Color.parseColor("#ededbd"));
               // cell.setBackgroundResource(R.drawable.calender_border);

            }

            if(dateWithCount.get(cellDate)!=null && !dateWithCount.get(cellDate).isEmpty()){

                String count=dateWithCount.get(cellDate);


                //cell.setBackgroundColor(Color.parseColor("#0000FF"));


               // cell.setBackgroundResource(R.drawable.calender_border);

                cell.setHint(cell.getText());

                String textOnDate="<p align=\"center\" valign=\"middle\" style=\"padding-top:10px; height:20px; width:20px; vertical-align:middle; display:table-cell;\">"+cell.getText().toString()+"<b color='#0000FF' style='background-color:#0000FF; margin:10px;'><i>"+count+"</i><b></p>";

                Spannable spanText=new SpannableString(textOnDate);

                cell.setText(Html.fromHtml(spanText.toString()));

            }
            else {
                cell.setHint(cell.getText());
                String textOnDate="<p align=\"center\" valign=\"middle\" style=\"padding-top:10px; height:20px; width:20px; vertical-align:middle; display:table-cell;\">"+cell.getText().toString()+"<b color=\"#0000FF\" style=\"margin:10px;\"><i>&nbsp;</i></b></p>";
                Spannable spanText=new SpannableString(textOnDate);
                cell.setText(Html.fromHtml(spanText.toString()));

            }
        }
        else {

            appPref=new AppPref(context);
            if(dateSelected.equals(cellDate)){
                cell.setBackgroundColor(Color.parseColor("#ededbd"));
            }
            else {

                if(absentDates.contains(cellDate)){
                    //cell.setBackgroundColor(Color.parseColor("#EF5350"));
                    cell.setBackgroundResource(R.drawable.red_calender_border);
//                    appPref.setOp_cal_click("no");
//                    Log.e("Op cal click->set",""+appPref.getOp_cal_click());

                }
                else if(workingDates.contains(cellDate)){
                   // cell.setBackgroundColor(Color.parseColor("#FFA726"));
                    cell.setBackgroundResource(R.drawable.orange_calender_border);
//                    appPref.setOp_cal_click("yes");
//                    Log.e("Op cal click>set",""+appPref.getOp_cal_click());
                }
                else
                    {
                    //cell.setBackgroundColor(Color.parseColor("#9CCC65"));
                    cell.setBackgroundResource(R.drawable.green_calender_border);
//                        appPref.setOp_cal_click("no");
//                        Log.e("Op cal click>set",""+appPref.getOp_cal_click());
                }
            }

            cell.setHint(cell.getText());
            String textOnDate="<p align=\"center\" valign=\"middle\" style=\"padding-top:10px; height:20px; width:20px; vertical-align:middle; display:table-cell;\">"+cell.getText().toString()+"<b color=\"#0000FF\" style=\"margin:10px;\"><i>&nbsp;</i></b></p>";
            Spannable spanText=new SpannableString(textOnDate);
            cell.setText(Html.fromHtml(spanText.toString()));
        }

    }

}
