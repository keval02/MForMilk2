package nivida.com.mformilk.CustomCalendar;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nivida.com.mformilk.AppPref;
import nivida.com.mformilk.CalenderTab.CalenderTabFragment;
import nivida.com.mformilk.R;


public class CustomCalendarView extends LinearLayout {
    private Context mContext;
    private View view;
    private ImageView previousMonthButton;
    private ImageView nextMonthButton;
    private CalendarListener calendarListener;
    private Calendar currentCalendar;
    private Locale locale;
    private Date lastSelectedDay;
    private Typeface customTypeface;
    private int firstDayOfWeek;
    private List<DayDecorator> decorators;
    private static final String DAY_OF_WEEK = "dayOfWeek";
    private static final String DAY_OF_MONTH_TEXT = "dayOfMonthText";
    private static final String DAY_OF_MONTH_CONTAINER = "dayOfMonthContainer";
    private int disabledDayBackgroundColor;
    private int disabledDayTextColor;
    private int calendarBackgroundColor;
    private int selectedDayBackground;
    private int weekLayoutBackgroundColor;
    private int calendarTitleBackgroundColor;
    private int selectedDayTextColor;
    private int calendarTitleTextColor;
    private int dayOfWeekTextColor;
    private int dayOfMonthTextColor;
    private int currentDayOfMonth;
    private int currentMonthIndex;
    private boolean isOverflowDateVisible;
    private OnClickListener onDayOfMonthClickListener;
    AppPref appPref;
    CalenderTabFragment calenderfragment;
   // Diary_M_Fragment diary_m_fragment;
   // private ImageView img_previous,img_after;

    public CustomCalendarView(Context mContext) {
        this(mContext, (AttributeSet)null);
    }

    public CustomCalendarView(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        this.firstDayOfWeek = Calendar.SUNDAY;
        this.decorators = null;
        this.currentMonthIndex = 0;
        this.isOverflowDateVisible = true;
        this.onDayOfMonthClickListener = new OnClickListener() {
            public void onClick(View view) {
                ViewGroup dayOfMonthContainer = (ViewGroup)view;
                String tagId = (String)dayOfMonthContainer.getTag();
                tagId = tagId.substring("dayOfMonthContainer".length(), tagId.length());
                TextView dayOfMonthText = (TextView)view.findViewWithTag("dayOfMonthText" + tagId);
                Calendar calendar = Calendar.getInstance();
                calendar.setFirstDayOfWeek(getFirstDayOfWeek());
                calendar.setTime(CustomCalendarView.this.currentCalendar.getTime());
                calendar.set(5, Integer.valueOf(dayOfMonthText.getHint().toString()).intValue());
               CustomCalendarView.this.markDayAsSelectedDay(calendar.getTime());
                CustomCalendarView.this.markDayAsCurrentDay(CustomCalendarView.this.currentCalendar);
                if(CustomCalendarView.this.calendarListener != null) {
                    CustomCalendarView.this.calendarListener.onDateSelected(calendar.getTime());
                }

            }
        };
        this.mContext = mContext;
        if(VERSION.SDK_INT < 3 || !this.isInEditMode()) {
            this.getAttributes(attrs);
            this.initializeCalendar();
        }
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray typedArray = this.mContext.obtainStyledAttributes(attrs, com.imanoweb.calendarview.R.styleable.CustomCalendarView, 0, 0);
        this.calendarBackgroundColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_calendarBackgroundColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.white));
        this.calendarTitleBackgroundColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_titleLayoutBackgroundColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.white));
        this.calendarTitleTextColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_calendarTitleTextColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.black));
        this.weekLayoutBackgroundColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_weekLayoutBackgroundColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.white));
        this.dayOfWeekTextColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_dayOfWeekTextColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.black));
        this.dayOfMonthTextColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_dayOfMonthTextColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.black));
        this.disabledDayBackgroundColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_disabledDayBackgroundColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.day_disabled_background_color));
        this.disabledDayTextColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_disabledDayTextColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.day_disabled_text_color));
        this.selectedDayBackground = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_selectedDayBackgroundColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.selected_day_background));
        this.selectedDayTextColor = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_selectedDayTextColor, this.getResources().getColor(com.imanoweb.calendarview.R.color.white));
        this.currentDayOfMonth = typedArray.getColor(com.imanoweb.calendarview.R.styleable.CustomCalendarView_currentDayOfMonthColor, this.getResources().getColor(R.color.current_day_of_month));
        typedArray.recycle();

    }

    ////custom calender urvi

    private void initializeCalendar() {
        LayoutInflater inflate = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = inflate.inflate(R.layout.custom_calendar_urvi, this, true);
        this.previousMonthButton = (ImageView)this.view.findViewById(R.id.leftButton);
        this.nextMonthButton = (ImageView)this.view.findViewById(R.id.rightButton);
//        appPref=new AppPref(getContext());
//        diary_m_fragment=new Diary_M_Fragment();
//        if(diary_m_fragment.getUserVisibleHint())
//        {
//            //this.previousMonthButton.setBackgroundDrawable( getResources().getDrawable(R.drawable.ready) );
//            Toast.makeText(getContext(), "Diary", Toast.LENGTH_SHORT).show();
//            Log.e("visible","visible");
//        }
//
//      else
//        {
//            Log.e("invisible","invisible");
//            Toast.makeText(getContext(), "Task", Toast.LENGTH_SHORT).show();
//        }


        this.previousMonthButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CustomCalendarView.this.currentMonthIndex--;
                CustomCalendarView.this.currentCalendar = Calendar.getInstance(Locale.getDefault());
                CustomCalendarView.this.currentCalendar.add(2, CustomCalendarView.this.currentMonthIndex);
                CustomCalendarView.this.refreshCalendar(CustomCalendarView.this.currentCalendar);
                if(CustomCalendarView.this.calendarListener != null) {
                    CustomCalendarView.this.calendarListener.onMonthChanged(CustomCalendarView.this.currentCalendar.getTime());
                }

            }
        });
        this.nextMonthButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CustomCalendarView.this.currentMonthIndex++;
                CustomCalendarView.this.currentCalendar = Calendar.getInstance(Locale.getDefault());
                CustomCalendarView.this.currentCalendar.add(2, CustomCalendarView.this.currentMonthIndex);
                CustomCalendarView.this.refreshCalendar(CustomCalendarView.this.currentCalendar);
                if(CustomCalendarView.this.calendarListener != null) {
                    CustomCalendarView.this.calendarListener.onMonthChanged(CustomCalendarView.this.currentCalendar.getTime());
                }

            }
        });
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        Calendar currentCalendar = Calendar.getInstance(locale);
        this.setFirstDayOfWeek(1);
        this.refreshCalendar(currentCalendar);
    }

    public void setBGResourceForPreviousMonth(int resID){
        this.previousMonthButton.setImageResource(resID);
    }

    public void setBGResourceForNextMonth(int resID){
        this.nextMonthButton.setImageResource(resID);
    }


    private void initializeTitleLayout() {
        View titleLayout = this.view.findViewById(com.imanoweb.calendarview.R.id.titleLayout);
        titleLayout.setBackgroundColor(this.calendarTitleBackgroundColor);
        String dateText = (new DateFormatSymbols(this.locale)).getShortMonths()[this.currentCalendar.get(2)].toString();
        dateText = dateText.substring(0, 1).toUpperCase() + dateText.subSequence(1, dateText.length());
        TextView dateTitle = (TextView)this.view.findViewById(com.imanoweb.calendarview.R.id.dateTitle);
        dateTitle.setTextColor(this.calendarTitleTextColor);
        dateTitle.setText(dateText + " " + this.currentCalendar.get(1));
        dateTitle.setTextColor(this.calendarTitleTextColor);
        if(null != this.getCustomTypeface()) {
            dateTitle.setTypeface(this.getCustomTypeface(), 1);
        }

    }

    @SuppressLint({"DefaultLocale"})
    private void initializeWeekLayout() {
        View titleLayout = this.view.findViewById(com.imanoweb.calendarview.R.id.weekLayout);
        titleLayout.setBackgroundColor(this.weekLayoutBackgroundColor);
        String[] weekDaysArray = (new DateFormatSymbols(this.locale)).getShortWeekdays();

        for(int i = 1; i < weekDaysArray.length; ++i) {
            String dayOfTheWeekString = weekDaysArray[i];
            dayOfTheWeekString = dayOfTheWeekString.substring(0, 3).toUpperCase();
            TextView dayOfWeek = (TextView)this.view.findViewWithTag("dayOfWeek" + this.getWeekIndex(i, this.currentCalendar));
            dayOfWeek.setText(dayOfTheWeekString);
            dayOfWeek.setTextColor(this.dayOfWeekTextColor);
            if(null != this.getCustomTypeface()) {
                dayOfWeek.setTypeface(this.getCustomTypeface());
            }
        }

    }

    private void setDaysInCalendar() {
        Calendar calendar = Calendar.getInstance(this.locale);
        calendar.setTime(this.currentCalendar.getTime());
        calendar.set(5, 1);
        calendar.setFirstDayOfWeek(this.getFirstDayOfWeek());
        int firstDayOfMonth = calendar.get(7);
        int dayOfMonthIndex = this.getWeekIndex(firstDayOfMonth, calendar);
        int actualMaximum = calendar.getActualMaximum(5);
        Calendar startCalendar = (Calendar)calendar.clone();
        startCalendar.add(5, -(dayOfMonthIndex - 1));
        int monthEndIndex = 42 - (actualMaximum + dayOfMonthIndex - 1);

        DayView dayView;
        for(int weekRow = 1; weekRow < 43; ++weekRow) {
            ViewGroup dayOfMonthContainer = (ViewGroup)this.view.findViewWithTag("dayOfMonthContainer" + weekRow);
            dayView = (DayView)this.view.findViewWithTag("dayOfMonthText" + weekRow);
            if(dayView != null) {
                dayOfMonthContainer.setOnClickListener((OnClickListener)null);
                dayView.bind(startCalendar.getTime(), this.getDecorators());
                dayView.setVisibility(0);
                if(null != this.getCustomTypeface()) {
                    dayView.setTypeface(this.getCustomTypeface());
                }

                if(this.isSameMonth(calendar, startCalendar)) {
                    dayOfMonthContainer.setOnClickListener(this.onDayOfMonthClickListener);
                    dayView.setBackgroundColor(this.calendarBackgroundColor);
                    dayView.setTextColor(this.dayOfWeekTextColor);
                } else {
                    dayView.setBackgroundColor(this.disabledDayBackgroundColor);
                    dayView.setTextColor(this.disabledDayTextColor);
                    if(!this.isOverflowDateVisible()) {
                        dayView.setVisibility(8);
                    } else if(weekRow >= 36 && (float)monthEndIndex / 7.0F >= 1.0F) {
                        dayView.setVisibility(8);
                    }
                }

                dayView.decorate();
                this.markDayAsCurrentDay(startCalendar);
                startCalendar.add(5, 1);
                ++dayOfMonthIndex;
            }
        }

        ViewGroup var10 = (ViewGroup)this.view.findViewWithTag("weekRow6");
        dayView = (DayView)this.view.findViewWithTag("dayOfMonthText36");
        if(dayView.getVisibility() != 0) {
            var10.setVisibility(8);
        } else {
            var10.setVisibility(0);
        }

    }

    public boolean isSameMonth(Calendar c1, Calendar c2) {
        return c1 != null && c2 != null?c1.get(0) == c2.get(0) && c1.get(1) == c2.get(1) && c1.get(2) == c2.get(2):false;
    }

    public static boolean isToday(Calendar calendar) {
        return isSameDay(calendar, Calendar.getInstance());
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The dates must not be null");
        }
    }

    private void clearDayOfTheMonthStyle(Date currentDate) {
        if(currentDate != null) {
            Calendar calendar = this.getTodaysCalendar();
            calendar.setFirstDayOfWeek(this.getFirstDayOfWeek());
            calendar.setTime(currentDate);
            DayView dayView = this.getDayOfMonthText(calendar);
            dayView.setBackgroundColor(this.calendarBackgroundColor);
            dayView.setTextColor(this.dayOfWeekTextColor);
        }

    }

    private DayView getDayOfMonthText(Calendar currentCalendar) {
        return (DayView)this.getView("dayOfMonthText", currentCalendar);
    }

    private int getDayIndexByDate(Calendar currentCalendar) {
        int monthOffset = this.getMonthOffset(currentCalendar);
        int currentDay = currentCalendar.get(5);
        int index = currentDay + monthOffset;
        return index;
    }

    private int getMonthOffset(Calendar currentCalendar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(this.getFirstDayOfWeek());
        calendar.setTime(currentCalendar.getTime());
        calendar.set(5, 1);
        int firstDayWeekPosition = calendar.getFirstDayOfWeek();
        int dayPosition = calendar.get(7);
        return firstDayWeekPosition == 1?dayPosition - 1:(dayPosition == 1?6:dayPosition - 2);
    }

    private int getWeekIndex(int weekIndex, Calendar currentCalendar) {
        int firstDayWeekPosition = currentCalendar.getFirstDayOfWeek();
        return firstDayWeekPosition == 1?weekIndex:(weekIndex == 1?7:weekIndex - 1);
    }

    private View getView(String key, Calendar currentCalendar) {
        int index = this.getDayIndexByDate(currentCalendar);
        View childView = this.view.findViewWithTag(key + index);
        return childView;
    }

    private Calendar getTodaysCalendar() {
        Calendar currentCalendar = Calendar.getInstance(this.mContext.getResources().getConfiguration().locale);
        currentCalendar.setFirstDayOfWeek(this.getFirstDayOfWeek());
        return currentCalendar;
    }

    @SuppressLint({"DefaultLocale"})
    public void refreshCalendar(Calendar currentCalendar) {
        this.currentCalendar = currentCalendar;
        this.currentCalendar.setFirstDayOfWeek(this.getFirstDayOfWeek());
        this.locale = this.mContext.getResources().getConfiguration().locale;
        this.initializeTitleLayout();
        this.initializeWeekLayout();
        this.setDaysInCalendar();
    }

    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public void markDayAsCurrentDay(Calendar calendar) {
        if(calendar != null && isToday(calendar)) {
            DayView dayOfMonth = this.getDayOfMonthText(calendar);
            dayOfMonth.setTextColor(this.currentDayOfMonth);
//            dayOfMonth.setBackgroundDrawable(getResources().getDrawable(R.drawable.darkgreen));
        }

    }

    public void markDayAsSelectedDay(Date currentDate) {
        Calendar currentCalendar = this.getTodaysCalendar();
        currentCalendar.setFirstDayOfWeek(this.getFirstDayOfWeek());
        currentCalendar.setTime(currentDate);
        this.clearDayOfTheMonthStyle(this.lastSelectedDay);
        this.storeLastValues(currentDate);
        DayView view = this.getDayOfMonthText(currentCalendar);
        view.setBackgroundColor(this.selectedDayBackground);
        view.setTextColor(this.selectedDayTextColor);
    }

    private void storeLastValues(Date currentDate) {
        this.lastSelectedDay = currentDate;
    }

    public void setCalendarListener(CalendarListener calendarListener) {
        this.calendarListener = calendarListener;
    }

    public List<DayDecorator> getDecorators() {
        return this.decorators;
    }

    public void setDecorators(List<DayDecorator> decorators) {
        this.decorators = decorators;
    }

    public boolean isOverflowDateVisible() {
        return this.isOverflowDateVisible;
    }

    public void setShowOverflowDate(boolean isOverFlowEnabled) {
        this.isOverflowDateVisible = isOverFlowEnabled;
    }

    public void setCustomTypeface(Typeface customTypeface) {
        this.customTypeface = customTypeface;
    }

    public Typeface getCustomTypeface() {
        return this.customTypeface;
    }

    public Calendar getCurrentCalendar() {
        return this.currentCalendar;
    }
}

