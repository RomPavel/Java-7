import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class Product implements Serializable {
    static int ID = 1;

    int id;
    String name;
    double price;
    int col;
    public final Date creationDate = new Date();

    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    Product(String name, double price, int col) {
        this.id=ID++;
        this.name = name;
        this.price = price;
        this.col = col;
    }

    Product() {
        this("Rice", 1.0, 1);
    }

    void SetInfo(String name, double price, int col) {
        this.name = name;
        this.price = price;
        this.col = col;
    }

    @Override
    public String toString()
    {
        return ""+this.id+" "+this.name+"   "+this.price+"$    "+this.col+"        "+AppLocale.getString(AppLocale.creation)+":"+this.getCreationDate();
    }
}
