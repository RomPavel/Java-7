import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Locale;

public class Main {

    public static LinkedList<Product> createAss(){
        LinkedList<Product> ass = new LinkedList<>();
        String tr = AppLocale.getString(AppLocale.milk);
        ass.add(new Product(AppLocale.getString(AppLocale.milk), 2, 2));
        ass.add(new Product(AppLocale.getString(AppLocale.cola), 3.2, 1));
        ass.add(new Product(AppLocale.getString(AppLocale.rice), 5, 3));

        return ass;
    }

    static Locale createLocale( String[] args )	{
        if ( args.length == 2 ) {
            return new Locale( args[0], args[1] );
        } else if( args.length == 4 ) {
            return new Locale( args[2], args[3] );
        }
        return null;
    }

    static void setupConsole(String[] args) {
        if ( args.length >= 2 ) {
            if ( args[0].compareTo("-encoding")== 0 ) {
                try {
                    System.setOut( new PrintStream( System.out, true, args[1] ));
                } catch ( UnsupportedEncodingException ex ) {
                    System.err.println( "Unsupported encoding: " + args[1] );
                    System.exit(1);
                }
            }
        }
    }
    public static void main(String[] args) {


        setupConsole(args);

        Locale loc = createLocale(args);
        if (loc == null) {
            System.err.println(
                    "Invalid argument(s)\n" +
                            "Syntax: [-encoding ENCODING_ID] language country\n" +
                            "Example: -encoding Cp855 be BY");
            System.exit(1);
        }

        AppLocale.set(loc);

        try {
            Connector prod = new Connector("prod.dat");


            InternetShop iShop = new InternetShop();

            iShop.pullAssortment(/*prod.read()*/createAss());

            iShop.pullClient("Alex");
            iShop.pullClient("Mike");
            iShop.pullClient("Vlad");

            iShop.viewAssortment();

            //iShop.pullNewProduct("Polo", 2, 1);

            //iShop.viewAssortment();


            System.out.println(iShop.getClient("Alex").BuyProduct(AppLocale.getString(AppLocale.cola)));//Alex покупает Колу, Кола пропадет из списка
            iShop.viewAssortment();

            iShop.Admin.putToBlackList("Mike");//в черный список Mike
            System.out.println(iShop.getClient("Mike").BuyProduct(AppLocale.getString(AppLocale.milk)));//не смог купить - "false"


            prod.write(iShop.getAssortment());
        }
        catch ( Exception e )
        {
            System.err.println(e);
        }

    }
}
