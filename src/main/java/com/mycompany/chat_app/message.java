package com.mycompany.chat_app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class message {
    
    // ================ MESSAGE CLASS =================
    
    static class Message {
        
        private String messageID;
        private String recipient;
        private String messageText;
        private int messageNumber;
        private String messageHash;
        
        private static ArrayList<Message> sentMessages =
                new ArrayList<>();
        
        private static int totalMessage = 0;
        
        // Constructor
        public Message(String recipient,
                       String messageText,
                       int messageNumber) {
            this.messageID = generateMessageID();
            this.recipient = recipient;
            this.messageText = messageText;
            this.messageNumber = messageNumber;
            this.messageHash = createMessageHash();
        } 
        
        // Generate random 10 digit ID
        private String generateMessageID() {
            
            Random random = new Random();
            long number =
                    1000000000L +
                    (long)(random.nextDouble()
                    * 9000000000L);
            
            return String.valueOf(number);
        }
        //Getters
        public String getMessageID(){
            return messageID;
        }
        public String getRecipient(){
            return recipient;
        }
        public String getMessageText(){
            return messageText;
        }
        public String getMessageHash(){
            return messageHash;
        }
        
        //Check Message ID
        public boolean checkMessageID(){
            return messageID.length() <= 10;
        }
        
        //Check recipient number
        public String checkRecipientCell(){
            
            if (recipient.startsWith("+27")
                    && recipient.length() <= 13){
                return "Cell phone number successfully captured";
                
            }else{
                
                return "Cell phone number is incorrectly formatted "
                            + "or does not contain an international code"
                            + "Please correct the number and try again.";  
            }
        }
        
        //Check message length
        public String checkMessageLength(){
            if (messageText.length() <= 250){
                return "Messsage ready to send.";
             
            }else{
                
                int excess =
                        messageText.length() - 250;
                return "Message exceds 250 charecters by "
                        + excess
                        + ", please reduce the size.";      
            }
        }
        
        //Create hash
        public String createMessageHash(){
            
            String firstTwo =
                    messageID.substring(0, 2);
            
            String[] words =
                    messageText.split(" ");
            
            String firstWord =
                    words[0];
            
            String lastWord =
                    words[words.length - 1];
            
            return (firstTwo
                    + ":"
                    + messageNumber
                    + ":"
                    + firstWord
                    + lastWord).toUpperCase();
        }
        
        // Send / Store / Disregard
        public String SentMessage(int option){
        
            switch (option){
                
                case 1:
                    
                    sentMessages.add(this);
                    totalMessage++;
                    
                    return "Message successfully sent.";
                    
                case 2:
                    
                    return "Press 0 to delete the message.";
                    
                case 3:
                        
                    storeMessage();
                        
                    return "Message successfully stored.";
                        
                default:
                    
                    return "Invalid option.";     
            }
        }
        
        //Print message
        public static String printMessages(){
            
            String output = "";
            
            for (Message msg : sentMessages){
                
                output +=
                        "Message ID: "
                        + msg.getMessageID()
                        +"\n";
                
                output +=
                        "Message Hash: "
                        + msg.getMessageHash()
                        + "\n";
                
                output +=
                        "Recipient: "
                        + msg.getRecipient()
                        + "\n";
                
                output +=
                        "Message: "
                        +msg.getMessageText()
                        +"\n\n";
            }
            
            return output;
        }
        
        //Return total message
        public static int returnTotalMessages(){
            
            return totalMessage;
        }
        
        //Store message in JSON
        public void storeMessage(){
            
            try {
                
                FileWriter writer =
                        new FileWriter("message.json", true);
                
                writer.write("(\n");
                
                writer.write("\"MessageID\":\""
                        + messageID
                        + "\",\n");
                
                writer.write("\"Recipient\":\""
                        + recipient
                        + "\",\n");
                
                writer.write("\"Message\":\""
                        + messageText
                        + "\",\n");
                
                writer.write("\"Hash\":\""
                        + messageHash
                        + "\",\n");
                
                writer.write("}\n");
                
                writer.close();
                
            } catch (IOException e){
                
                System.out.println("Error storing message.");
            }
        }
    }
    //============ MAIN METHOD =============
    
    public static void main(String[] args) {
        
        
        Scanner input = new Scanner (System.in);
        
        System.out.println("Welcome to QuickChat");
        System.out.print("Enter number of messages: ");
        int numMessages = input.nextInt();
        input.nextLine();
        
        while (true) {
            
            System.out.println("\nl. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            
            System.out.print("Choose option: ");
            int choice = input.nextInt();
            input.nextLine();
             
            
            switch (choice) {
            
            case 1:
                
                for (int i = 0; i < numMessages; i++){
                
                System.out.println("\nMessage "
                        + (1 + 1));
                
                System.out.println("Enter recipient; ");
                String recipient =
                        input.nextLine();
                
                System.out.println("Enter message: ");
                String text =
                        input.nextLine();
                
                Message msg =
                        new Message(
                                recipient,
                                text,
                                i);
                //Check recipient
                System.out.println(
                        msg.checkRecipientCell());
                
                //Check message length
                System.out.println(
                        msg.checkMessageLength());
                    
                // Display Message ID
                System.out.println(
                         "Message ID generated: "
                        +msg.getMessageHash());
                    
                //Options
                System.out.println("\nl. Send message");
                System.out.println("2. Disregard Message");
                System.out.println("3. Store Message");
                       
                    
                int option =
                        input.nextInt();
                
                input.nextLine();
                
                System.out.println(
                        msg.SentMessage(option));
                
                // Show full details if sent
                if (option ==1){
                    
                    System.out.println(
                            "\nFULL MESSAGE DETAILS");
                    
                    System.out.println(
                          "Message ID: "
                                    + msg.getMessageID());
                    
                    System.out.println(
                          "Message Hash: "
                                    + msg.getMessageHash());
                    
                    System.out.println(
                          "Recipient: "
                                    + msg.getRecipient());
                    
                    System.out.println(
                          "Message : "
                                    + msg.getMessageText());
                    
                }
                       
            }
                
            break;
            
            case 2:
                
                System.out.println("Coming soon");
                break;
                
            case 3:
                
                System.out.println(
                        "\nTotal message sent: "
                        + Message.returnTotalMessages());
                
                System.out.println("GoodBye");
                
                System.exit(0);
                
                default:
                     
                     System.out.println("Invalid option");
             
        }
    }
  }
}