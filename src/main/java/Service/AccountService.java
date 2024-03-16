package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;


    //no arg constructor
public AccountService(){
    accountDAO = new AccountDAO();
} 
    //constructor with parameter
public AccountService(AccountDAO accountDAO){
    this.accountDAO = accountDAO;
}

public Account createAccount(Account account){
    if (account.getUsername() == null || account.getUsername().isEmpty()){
        return null;
    }
    else if (account.getPassword() == null || account.getPassword().length() < 4 || account.getPassword().isEmpty()){
        return null;
    }
  
    
       return accountDAO.createAccount(account); 
    
    
}
public Account login(String username, String password) {
    return accountDAO.login(username, password);
}
/*public Account getAccountByLogin(String username, String password) {
    return accountDAO.login(username, password);
}
*/

}
