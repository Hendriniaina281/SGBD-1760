package clientsql;

import function.Function;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import table.Table;


public class ClientTraitement {
    int port;
    String ip;
    Socket clientSocket;
    DataInputStream datainput;
    DataOutputStream output;
    ObjectInputStream objectInput;
    
    public ObjectInputStream getObjectInput() {
        return objectInput;
    }

    public void setObjectInput(ObjectInputStream objectInput) {
        this.objectInput = objectInput;
    }
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public DataInputStream getDatainput() {
        return datainput;
    }

    public void setDatainput(DataInputStream datainput) {
        this.datainput = datainput;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
    
    
    public ClientTraitement(String ip,int port){
        this.ip = ip;
        this.port = port;
        
        try{
            this.setClientSocket(new Socket(ip,port));
            this.setDatainput(new DataInputStream(this.getClientSocket().getInputStream()));
            this.setOutput(new DataOutputStream(this.getClientSocket().getOutputStream()));
            this.setObjectInput(new ObjectInputStream(this.getClientSocket().getInputStream()));
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void commandRequest() throws IOException, Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String request= new String();
        String response = new String();
        Function fu = new Function();
        Table table = new Table();
        
        try{
            while(request!=null){
                System.out.print("SQBD>");
                request = br.readLine();
                this.getOutput().writeUTF(request);
                this.getOutput().flush();
                
                
                Object object = this.getObjectInput().readObject();
                
                if(object instanceof Table){
                    Table tables = (Table)object;
                    fu.print(tables);
                }else{
                    String str = (String)object;
                    System.out.println(str);
                }
                
                
            }
            
        }catch(Exception ex){
           throw ex; 
        }finally{
            this.getObjectInput().close();
            
        }
        
    }
}
