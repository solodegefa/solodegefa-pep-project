package DAO;
import Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.ConnectionUtil;

public class MessageDAO {
     //Retrieve all messages
     public List<Message> getAllMessages(){
        //create connection
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
     try{
        // SQL logic
        String sql = "SELECT * FROM Message";

        //prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //result set
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Message message = new Message(resultSet.getInt("message_id"), 
                        resultSet.getInt("posted_by"),
                        resultSet.getString("message_text"),
                        resultSet.getLong("time_posted_epoch"));

                        messages.add(message);
        }

     }catch(SQLException e){
        System.out.println(e.getMessage());
     }   
        return messages;
    }
    //create meassage
public Message createMessage(int posted_by, String message_text, long time_posted_epoch){
   
   
   try{ Connection connection = ConnectionUtil.getConnection();
    //sql logic
    String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Set string and set int
    preparedStatement.setInt(1, posted_by);
    preparedStatement.setString(2, message_text);
    preparedStatement.setLong(3, time_posted_epoch);
        //execute update
    int affectedRows =  preparedStatement.executeUpdate();
    
    //
    if(affectedRows > 0){
     try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
         if (resultSet.next()){
             int messageId = resultSet.getInt(affectedRows);
             return new Message(messageId, posted_by, message_text, time_posted_epoch);
         }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }catch(SQLException e){
   System.out.println(e.getMessage());
    }
    return null;
}
public boolean updatedMessage(int message_id, String message_text) {
   
    try{ Connection connection = ConnectionUtil.getConnection();
        
        String sql = "UPDATE message SET message_text = ? WHERE message_id = ?"; 
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, message_id);
        preparedStatement.setString(message_id, message_text);

        int resultSet = preparedStatement.executeUpdate();

        return resultSet > 0;
        
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
         return false;
}

public void deleteMessage(int message_id){
        Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "DELETE FROM Message WHERE message_id = ?";   
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, message_id);

        int resultSet = preparedStatement.executeUpdate();

        if (resultSet > 0){
            //Message deletedMessage = new Message(1, 1, resultSet.getString(message_id), 0);
          return;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return;
}
    
public Message getMessageById(int message_id){
        //connection
        Connection connection = ConnectionUtil.getConnection();
        try{
        //Sql logic
        String sql = "SELECT * FROM Message WHERE message_id = ?";

        //prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        //SetInt
        preparedStatement.setInt(1, message_id);
        
        //Result set
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Message message = new Message(resultSet.getInt("message_id"),
                            resultSet.getInt("posted_by"),
                            resultSet.getString("message_text"),
                            resultSet.getLong("time_posted_epoch"));
                            return message;
        }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Message> getMessageByUserId(int user_id){
        //connection
        
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageByUserID = new ArrayList<>();
        try{
        //Sql logic
        String sql = "SELECT * FROM Message WHERE posted_by = ?";

        //prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        //SetInt
        preparedStatement.setInt(1, user_id);
        
        //Result set
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Message message = new Message(resultSet.getInt("message_id"),
                            resultSet.getInt("posted_by"),
                            resultSet.getString("message_text"),
                            resultSet.getLong("time_posted_epoch"));

                            messageByUserID.add(message);
        }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messageByUserID;
    }
public void validateMessage(Message message){
    
     if(getAccountById(message.getPosted_by()) == null){
        System.out.println("Invalid user!");
     }
     else if (message.getMessage_text().isBlank()){
        System.out.println("Please enter valid message!");
     }
     else if(message.getMessage_text().length() > 255){
        System.out.println("The maximum characters allowed is 255");
     }
}
private Object getAccountById(int posted_by) {
    return null;
}
public Message deleteMessage(boolean b) {
    return null;
}

    
}
