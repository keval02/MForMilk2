package nivida.com.mformilk.SubscriptionTab;

import android.app.TimePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.DayDecorator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import nivida.com.mformilk.CustomCalendar.CalMainActivity;
import nivida.com.mformilk.CustomCalendar.CustomCalendarView;
import nivida.com.mformilk.CustomCalendar.NumberDecorator;
import nivida.com.mformilk.R;

import static nivida.com.mformilk.R.drawable.calender;

public class CheckOut extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Calendar calendar;
    RadioGroup group;
    String format;
    EditText et_delivery_time;
    private int CalendarHour, CalendarMinute;
    RadioButton daily,recurrent;
    CustomCalendarView calendarView;
    TimePickerDialog timepickerdialog;
    TextView date;
    Spinner spin;
    String[] Day = { "1Day", "3Day", "7Day", "15Day", "1Month"};
    HashMap<String,String> dateWithCounts=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        fetchIDs();
        setupToolbar();
    }

    private void fetchIDs() {
      //  et_delivery_time= (EditText) findViewById(R.id.deliverytime);
        group= (RadioGroup) findViewById(R.id.radioGroup);
      //  date= (TextView) findViewById(R.id.date);
        spin = (Spinner) findViewById(R.id.dayselect);
        daily= (RadioButton) findViewById(R.id.radio_daily);
        recurrent= (RadioButton) findViewById(R.id.radio_recurrent);
        spin.setOnItemSelectedListener(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // TODO Auto-generated method stub
                if(recurrent.isChecked()) {
                    spin.setVisibility(View.VISIBLE);

                } else if(daily.isChecked()) {
                    spin.setVisibility(View.GONE);

                }
            }
        });
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Day);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);
        List<DayDecorator> dayDecoratorList = new ArrayList<>();

        dayDecoratorList.add(new NumberDecorator(dateWithCounts));
        calendarView.setDecorators(dayDecoratorList);
        calendarView.refreshCalendar(currentCalendar);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String selectedDate=df.format(date);
                List<DayDecorator> dayDecoratorList = new ArrayList<>();
                dayDecoratorList.add(new NumberDecorator(dateWithCounts,selectedDate));
                calendarView.setDecorators(dayDecoratorList);
                calendarView.refreshCalendar(calendarView.getCurrentCalendar());


               // Toast.makeText(CheckOut.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
               // Toast.makeText(CheckOut.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });


//        et_delivery_time.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                calendar = Calendar.getInstance();
////                CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
////                CalendarMinute = calendar.get(Calendar.MINUTE);
//                calendar.set(Calendar.HOUR, 0);
//                calendar.set(Calendar.MINUTE, 0);
//
//
//                timepickerdialog = new TimePickerDialog(CheckOut.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
//                                    // Marshmallow+
//
//                                    if (hourOfDay == 0) {
//
//                                        hourOfDay += 12;
//
//                                        format = "A.M.";
//                                    } else if (hourOfDay == 12) {
//
//                                        format = "P.M.";
//
//                                    } else if (hourOfDay > 12) {
//
//                                        hourOfDay -= 12;
//
//                                        format = "P.M.";
//
//                                    } else {
//
//                                        format = "A.M.";
//                                    }
//
//
//                                } else {
//                                    //below Marshmallow
//
//                                    if (hourOfDay == 0) {
//
//                                        hourOfDay += 12;
//
//                                        format = "AM";
//                                    } else if (hourOfDay == 12) {
//
//                                        format = "PM";
//
//                                    } else if (hourOfDay > 12) {
//
//                                        hourOfDay -= 12;
//
//                                        format = "PM";
//
//                                    } else {
//
//                                        format = "AM";
//                                    }
//
//                                }
//
//
////                                if(minute>=0 && minute<10)
////                                {
////                                    minute= Integer.parseInt("0"+minute);
////                                }
////                                else
////                                {
////
////                                }
//
//                                //edt_time.setText(hourOfDay + ":" + minute + " "+format);
//
//                                et_delivery_time.setText((String.format("%02d:%02d %s", hourOfDay, minute, format)).toString().trim().replace(".", ""));
//                            }
//                        }, CalendarHour, CalendarMinute, false);
//                timepickerdialog.show();
//
//            }
//        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
