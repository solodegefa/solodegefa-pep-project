package Controller;

import java.util.List;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
   MessageService messageService;

public SocialMediaController(){
    this.accountService = new AccountService();
   this.messageService = new MessageService();

}
    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;

}
    public SocialMediaController(MessageService messageService){
        this.messageService = messageService;

}
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::createAccountHandler);
        app.post("/login", this::loginHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessageByUserIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        
        
        

        return app;
    }
    
    private void createAccountHandler(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        Account newUser = accountService.createAccount(account);
        if (newUser != null){
            ctx.json(newUser);
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }

    private void loginHandler(Context ctx){
        

        Account account = ctx.bodyAsClass(Account.class);
       // String username = account.getUsername();
       // String password = account.getPassword();

        Account userAccount = accountService.login(account.getUsername(), account.getPassword());
        if(account != null){
            ctx.json(userAccount);
        }
            ctx.status(401);
        
    }   
    
     private void getAllMessagesHandler(Context ctx){
        List<Message> getMessages = messageService.getAllMessages();
        if(getMessages != null){
            ctx.json(getMessages);
        }else{
            ctx.status(400);
        }
        
        
    }
    private void createMessageHandler(Context ctx){
        Message newMessage = ctx.bodyAsClass(Message.class);
      // long timePost = System.currentTimeMillis();
        Message message = messageService.CreateMessages(newMessage.getPosted_by(), newMessage.getMessage_text(), newMessage.time_posted_epoch);

        if(message != null){
            ctx.json(message);
        }else{
            ctx.status(400);
        }
        
    }
    private void getMessageByIdHandler(Context ctx){
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);

        if(message != null){
            ctx.json(message);

        }
    }
    private void deleteMessageHandler(Context ctx){
        int delMessage = Integer.parseInt(ctx.pathParam("message_id"));
        Message delete = (messageService.deleteMessage(delMessage));
        if (delete != null){
            ctx.json(delete);
            ctx.status(200);
        }

    }
    private void getMessageByUserIdHandler(Context ctx){
        int userId = Integer.parseInt(ctx.pathParam("posted_by"));
        List <Message> message = messageService.getMessageByUserId(userId);

        if(message != null){
            ctx.json(message);

        }
    }
      private void updateMessageHandler(Context ctx){
        int messageId = Integer.parseInt(ctx.pathParam("message_id"));
        String updatedMessage = ctx.bodyAsClass(Message.class).getMessage_text();
        boolean update = messageService.updatedMessage(messageId, updatedMessage);

        if(update){
            ctx.json(update);
            ctx.status(200);
        }else{
            ctx.status(400);
        }
    }
            /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     

    */
    }



