package nivida.com.mformilk.CustomCalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import nivida.com.mformilk.R;


public class CalMainActivity extends AppCompatActivity {

    CustomCalendarView calendarView;
    HashMap<String,String> dateWithCounts=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_main);

        fetchIDs();
    }

    private void fetchIDs() {
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);
        List<DayDecorator> dayDecoratorList = new ArrayList<>();

        fillDates();

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

                Toast.makeText(CalMainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(CalMainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillDates() {
        dateWithCounts.put("12-04-2017","5");
        dateWithCounts.put("18-10-2017","2");
        dateWithCounts.put("23-05-2017","9");
        dateWithCounts.put("05-05-2017","9");
    }
}
