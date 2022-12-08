package server;

import function.Function;
import serveursql.ServerTraitement;
import java.io.*;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) {
        ServerTraitement serTrait = null;
        try{
            serTrait = new ServerTraitement(2022);
            serTrait.getCommandRequest();
        }catch(Exception ex){
           ex.printStackTrace(); 
        }
    }
    
}
