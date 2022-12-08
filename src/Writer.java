package write;

import java.io.*;

public class Writer {
    public void write(String filename,String[] values){
        try {
            File file = new File(filename+".txt");
            FileWriter filewriter = new FileWriter(file,true);
            FileReader fr = new FileReader(file);
            BufferedWriter bw = new BufferedWriter(filewriter);
            BufferedReader br = new BufferedReader(fr);
            String read = br.readLine();

            if(read!=null){
                bw.write("\n");
            }

            for (int j = 0; j < values.length; j++) {
                bw.write(values[j]+",");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
