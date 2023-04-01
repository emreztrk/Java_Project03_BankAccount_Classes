package Project03;

import java.util.List;

public class Customer {

    String username;
    String password;
    List<Account> accountList;

    public Customer(String username, String password, List<Account> accountList){
        this.username=username;
        this.password=password;
        this.accountList=accountList;
    }
}
