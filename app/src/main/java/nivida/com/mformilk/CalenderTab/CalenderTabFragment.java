package nivida.com.mformilk.CalenderTab;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.DayDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import nivida.com.mformilk.AppPref;
import nivida.com.mformilk.CustomCalendar.CustomCalendarView;
import nivida.com.mformilk.CustomCalendar.NumberDecorator;
import nivida.com.mformilk.MainActivity;
import nivida.com.mformilk.R;

/**
 * Created by Jauhar xlr on 4/18/2016.
 *  mycreativecodes.in
 */
public class CalenderTabFragment extends Fragment {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    private int CalendarHour, CalendarMinute;
    String format;
    Calendar calendar;
    TimePickerDialog timepickerdialog;
    Date date1;
    DatePickerDialog datePickerDialog;
    int mYear, mMonth, mDay;
    String date_to_be_send;
    TextView edt_date;
    MaterialCalendarView widget;
    LinearLayout showlist;
    LinearLayout clicked;
    Date date2;
    MainActivity mainActivity;
    String selectedDateMonth="",selectedDateYear="";
    AppPref appPref;
    String dateForCount="";
    Date date;
    String str_currentmonth="",str_currentyear="";


    CustomCalendarView calendarView;
    HashMap<String,String> dateWithCounts=new HashMap<>();
    Date date3;
    String dateforoperator="";


    public CalenderTabFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getContext().getTheme().applyStyle(R.style.NoActionBarForCalenderPage, true); //blue ripple color
        final View view=inflater.inflate(R.layout.calender_tab_layout, container, false);


        appPref=new AppPref(getActivity());


        calendarView = (CustomCalendarView) view.findViewById(R.id.calendar_view);

        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);

        List<DayDecorator> dayDecoratorList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        int yearr = c.get(Calendar.YEAR);
        str_currentyear=Integer.toString(yearr);
        Log.e("YEAR",""+yearr);
        int monthh = c.get(Calendar.MONTH)+1;
        str_currentmonth=Integer.toString(monthh);
        Log.e("MONTH",""+monthh);



        dayDecoratorList.add(new NumberDecorator(dateWithCounts));

        calendarView.setDecorators(dayDecoratorList);
        calendarView.refreshCalendar(currentCalendar);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
                String selectedDate = df.format(date);
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate1 = df1.format(date);


                List<DayDecorator> dayDecoratorList = new ArrayList<>();
                dayDecoratorList.add(new NumberDecorator(dateWithCounts, selectedDate));
                calendarView.setDecorators(dayDecoratorList);
                calendarView.refreshCalendar(calendarView.getCurrentCalendar());
                String passString = df.format(date);


                //dateSplite=beanDiaryLists.get(position).getEventFromDate();
                try {
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    date3 = sdf.parse(selectedDate1);
                } catch (final ParseException e) {
                    e.printStackTrace();
                }
                dateforoperator = new SimpleDateFormat("dd-MM-yyyy").format(date3);
                //fromdate.setText("" + new SimpleDateFormat("dd-MMM-yyyy").format(date3));

                Log.e("datewithcounts", "" + dateWithCounts);
                Log.e("dateforoperator", "" + dateforoperator);
            }
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MMM-yyyy");

                SimpleDateFormat dfmonth = new SimpleDateFormat("M");
                //selectedDateMonth=dfmonth.format(date);
                str_currentmonth=dfmonth.format(date);

                SimpleDateFormat dfyear = new SimpleDateFormat("y");
                //selectedDateYear=dfyear.format(date);
                str_currentyear=dfyear.format(date);
            }
        });


        return view;
    }
    public void onResume() {
        super.onResume();

        appPref=new AppPref(getActivity());


    }


}
