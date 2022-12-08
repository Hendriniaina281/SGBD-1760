package read;

import java.io.*;
import java.util.*;

public class Read{
    public ArrayList<String> read(String fileName){
        ArrayList<String> array = new ArrayList<>();

        try{
            File file = new File(fileName+".txt");
            FileReader fileread = new FileReader(file);
            BufferedReader br = new BufferedReader(fileread);
            String lines = br.readLine();

            while(lines!=null){
                array.add(lines);
                lines = br.readLine();
            }
            //System.out.println("array: "+lines);
        
        br.close();
            
        }catch(Exception ex){

        }
        return array;
    }
}
