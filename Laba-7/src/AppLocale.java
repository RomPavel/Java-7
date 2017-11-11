import java.util.*;

public class AppLocale {
	private static final String strMsg = "Msg";
	private static Locale loc = Locale.getDefault();
	private static ResourceBundle res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );

	static Locale get() {
		return AppLocale.loc;
	}
	
	static void set( Locale local ) {
		loc = local;

		res = ResourceBundle.getBundle( strMsg, loc);


	}
	
	static ResourceBundle getBundle() {
		return res;
	}
	
	static String getString( String key ) {
	    String str = res.getString(key);
		return str;
	}

	// Resource keys:

	public static final String product="product";
	public static final String info="info";
	public static final String creation="creation";
	public static final String assortment="assortment";
	public static final String milk = "milk";
	public static final String cola = "cola";
	public static final String rice = "rice";
	public static final String name = "name";
	public static final String price = "price";
	public static final String amount = "amount";

}
