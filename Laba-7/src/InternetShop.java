
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class InternetShop {

   private  Map<String, Product>  assortment;
   private Map<String, Client> clientBase;
   Administrator Admin;

    InternetShop()
    {
       this.assortment = new HashMap<>();
       Admin= new Administrator();
    }

    void pullAssortment(LinkedList<Product> products)
    {
        for(Product prod:  products )
        {
            this.assortment.put(prod.name, prod);
        }
    }

    void pullNewProduct(String name, double price, int col){

        Product neew = new Product(name, price, col);
        this.assortment.put(name, neew);
    }

    LinkedList<Product> getAssortment(){
        LinkedList<Product> ass = new LinkedList<>();
        for(String key: this.assortment.keySet())
            ass.add(this.assortment.get(key));

        return ass;
    }

    void pullClient(String login){
        clientBase.put(login, new Client(login));
    }

    Client getClient(String login){
        return this.clientBase.get(login);
    }



    public boolean Buy(String name)
    {
        if(this.assortment.containsKey(name))
        {
            this.assortment.get(name).col--;
        }
        else
            return false;

        if(this.assortment.get(name).col<=0)
        {
            this.assortment.remove(name);
        }
        return true;
    }

    void viewAssortment()
    {
        System.out.println("--"+AppLocale.getString(AppLocale.name)+"--"+AppLocale.getString(AppLocale.price)+"--"+AppLocale.getString(AppLocale.amount));
        for(String key: this.assortment.keySet())
            System.out.println(this.assortment.get(key));
    }

    public class Client {
        String login;

        Client(String login)
        {
            this.login=login;
        }

        boolean BuyProduct(String name)
        {
           if(Admin.inBlackList(this.login))
               return false;
           else
            return  InternetShop.this.Buy(name);
        }
    }

    public class Administrator {
        private String name;
        private  Map<String, Client> blackList;

        Administrator(){
            this.name="Pavel";

            clientBase = new HashMap<>();
            blackList = new HashMap<>();
        }
        void fillClientBase(LinkedList<Client> clients) {

            for (Client ct : clients) {
                clientBase.put(ct.login, ct);
            }
        }

        void putToBlackList(String login)
        {
            blackList.put(login, clientBase.get(login));
        }

        public boolean inBlackList(String login)
        {
            return (blackList.containsKey(login));
        }
    }

}
