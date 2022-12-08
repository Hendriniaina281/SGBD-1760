package client;

import java.io.*;
import clientsql.ClientTraitement;

public class Client {
    public static void main(String[] args) {
        ClientTraitement cliTrait = null;
        
        try{
            cliTrait = new ClientTraitement("127.0.0.1",2022);
            cliTrait.commandRequest();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}
