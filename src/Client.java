package client;

import java.io.*;
import clientsql.ClientTraitement;

public class Client {
    public static void main(String[] args) {
        ClientTraitement cliTrait = null;
        
        try{
            cliTrait = new ClientTraitement("127.0.0.1",1760);
            cliTrait.commandRequest();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}
