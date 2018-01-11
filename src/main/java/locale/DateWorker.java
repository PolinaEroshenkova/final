package locale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateWorker {

    public String receiveDBformatDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public String receiveFormatDateByLocale() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return format.format(new Date());
    }

    public String receiveFormatDateByLocale(Date date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return format.format(date);
    }
}
