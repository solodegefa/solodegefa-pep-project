package DAO;
import static org.mockito.ArgumentMatchers.refEq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    public AccountDAO(){}
    
    public Account createAccount(Account account){
        //create a connection
       
        try{ Connection connection = ConnectionUtil.getConnection();
            //SQL code for insertion
            String sql = "INSERT INTO Account (username, password) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            //Set string method
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
    
              //execute
              preparedStatement.execute();
              //result set
              ResultSet pkResultSet = preparedStatement.getGeneratedKeys();
              if (pkResultSet.next()){
                  
              int generated_account_id = (int) pkResultSet.getLong(1);
              //account.setAccount_id(generated_account_id);
             return new Account(generated_account_id, account.getUsername(), account.getPassword());
                  //return account;
                  }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
    
        return null;
    }
  
        
   public Account login(String username, String password){
       
    
        try{ Connection connection = ConnectionUtil.getConnection();

            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return new Account(resultSet.getInt("account_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
        return null;
    }
    /*public Account checkAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Account newAccount = new Account(resultSet.getInt("account_id"),
                                                resultSet.getString("username"),
                                                resultSet.getString("password"));

                                                return newAccount;
            }

             }catch(SQLException e){
System.out.println(e.getMessage());
             }
        return null;
    }*/
   
   
}