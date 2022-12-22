package serveursql;

import function.Function;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import table.Table;

public class ServerTraitement {
    int port;
    ServerSocket serveurSocket;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    ObjectOutputStream objectOutput;
    //BufferedWriter bw;
    
    public ServerTraitement(int port) throws IOException{
        this.setPort(port);
        
        try{
            this.setServeurSocket(new ServerSocket(port));
            this.setSocket(this.getServeurSocket().accept());
            this.setInput(new DataInputStream(this.getSocket().getInputStream()));
            this.setOutput(new DataOutputStream(this.getSocket().getOutputStream()));
            this.setObjectOutput(new ObjectOutputStream(this.getSocket().getOutputStream()));
            this.getObjectOutput().flush();
            
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void getCommandRequest() throws Exception{
        String commande = new String();
        String response = new String();
        Table table = new Table();
        
        
            while(commande!=null){
                commande = this.getInput().readUTF();
                String[] arrayCom = commande.split(" ");
                Function fu = new Function();
                
                if(arrayCom[0].equals("create")){
                    response = fu.createTable(commande);
                    this.getObjectOutput().writeObject(response);
                    this.getObjectOutput().flush();   
                        
                }else if(arrayCom[0].equals("insert")){ 
                    response = fu.insert(commande);
                    this.getObjectOutput().writeObject(response);
                    this.getObjectOutput().flush();
                    
                }else if(arrayCom[0].equals("select") && (arrayCom[1].equals("*"))){
                    table = fu.select(commande);
                    //fu.print(table);
                    this.getObjectOutput().writeObject(table);
                    this.getObjectOutput().flush();
                    
                }else if(arrayCom[0].equals("select") && !(arrayCom[1].equals("*"))){
                    table = fu.projection(commande);
                    //fu.print(table);
                    this.getObjectOutput().writeObject(table);
                    this.getObjectOutput().flush();
                    
                }else if(arrayCom[0].equals("show")){
                    table = fu.showTable(commande);
                    this.getObjectOutput().writeObject(table);
                    this.getObjectOutput().flush();
                    
                }else if(arrayCom[0].equals("delete")){
                    response = fu.delete(commande);
                    this.getObjectOutput().writeObject(response);
                    this.getObjectOutput().flush(); 
                }else if(arrayCom[0].equals("produitcart")){
                    table = fu.produitCartesien(commande);
                    this.getObjectOutput().writeObject(table);
                    this.getObjectOutput().flush();
                }else if(arrayCom[0].equals("get") && (arrayCom[4].equals("join"))){
                    //System.out.println(arrayCom[4]);
                    table = fu.jointure(commande);
                    this.getObjectOutput().writeObject(table);
                    this.getObjectOutput().flush();
                }else if(arrayCom[0].equals("intersection")){
                    table = fu.intersection(commande);
                    this.getObjectOutput().writeObject(table);
                    this.getObjectOutput().flush();
                }else{
                    response = fu.erreur();
                    this.getObjectOutput().writeObject(response);
                    this.getObjectOutput().flush(); 
                }
                
            }
            
        
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServeurSocket() {
        return serveurSocket;
    }

    public void setServeurSocket(ServerSocket serveurSocket) {
        this.serveurSocket = serveurSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
    public ObjectOutputStream getObjectOutput() {
        return objectOutput;
    }

    public void setObjectOutput(ObjectOutputStream objectOutput) {
        this.objectOutput = objectOutput;
    }
    
   
    
}
