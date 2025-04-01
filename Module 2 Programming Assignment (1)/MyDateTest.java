import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDateTest {
    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);
        // year-month-day form
        System.out.println("Date 1: " + date1.getYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDay());
        System.out.println("Date 2: " + date2.getYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDay());
    }
}

class MyDate {
    private int year;
    private int month;
    private int day;

    // no-arg constructor for current date
    public MyDate() {
        this(System.currentTimeMillis());
    }

    // constructor for elapsed time
    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }

    // constructor for given inputs
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // set date given the elapsed time
    public void setDate(long elapsedTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
}
