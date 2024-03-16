package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;
import Util.ConnectionUtil;

public class MessageService {
    public MessageDAO messageDAO;

    //no arg constructor
public MessageService(){
    messageDAO = new MessageDAO();
    }
    //constructor with parameter
public MessageService(MessageDAO messageDAO){
    this.messageDAO = messageDAO;
}
//messageDao to retrieve all messages
public Message CreateMessages(int posted_by, String message_text, long time_posted_epoch){
    if(message_text != null && message_text != "" && message_text.length() <=  255){

    return messageDAO.createMessage(posted_by, message_text, time_posted_epoch);
    }
    return null;
}

public List<Message> getAllMessages() {
    return messageDAO.getAllMessages();
}

public Message deleteMessage(int delMessage){
    Message del = messageDAO.getMessageById(delMessage);
        messageDAO.deleteMessage(delMessage);
        return del;
}
public boolean updatedMessage(int message_id, String message_text) {
    if(message_id > 0 && message_text != null && message_text.length() <=  255){

        return messageDAO.updatedMessage(message_id, message_text);
        }
        
    return messageDAO.updatedMessage(message_id, message_text);
}
public Message getMessageById(int messageId) {
    return messageDAO.getMessageById(messageId);
}
public List <Message> getMessageByUserId(int userId) {
    return messageDAO.getMessageByUserId(userId);
}
public static Message CreateMessages(Message newMessage) {
    return null;
}
}
