package function;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import read.Read;
import table.Table;
import write.Writer;

public class Function {
    ArrayList<Table> allTable = new ArrayList<>();
    
    /*********************Message d'erreur***********************************/
    public String erreur(){
        String rep = "Commande introuvable";
        return rep;
        
    }
    /*********************************Fonction affichant tout les tables*********************************************************/
    public Table showTable(String request){
        File folder = new File("E:\\Tables\\");
        File[] listeFile = folder.listFiles();
        
        Table table = new Table();
        ArrayList<String> array = this.getNomTable();
        table.setColumnValue(array);
        table.setNom("Tout");
        //System.out.println("tsy vita marina"+array.get(0));
        
        return table;
        
    }
    
    public ArrayList<String> getNomTable(){
        File folder = new File("E:\\Tables\\");
        File[] listeFile = folder.listFiles();
        ArrayList<String> array = new ArrayList<>();
        Table tab = new Table();
        
        for(File file:listeFile){
            if(file.isFile() && file.getName().endsWith(".txt")){
                String[] nom = file.getName().split("\\.");
                array.add(nom[0]);
            }
        }
        
        return array;
        
    }
   
    /***************************************Fonction supprimant tous les elemens de la table****************************************************/
    public String delete(String request) throws Exception{
        String[] req = request.split(" ");
        PrintWriter writer = new PrintWriter("E:\\Tables\\"+req[2]+".txt");
        writer.print("");
        writer.close();
        
        String rep = "Elements supprimee";
        
        return rep;
    }
    
    /********************************************************Fonction Creer table******************************************************************/
    public String createTable(String request){
        Table table = null;
        String[] wordRequest = request.split(" ");
        String res = new String();
        try {
            
            File dossier = new File("E:\\Tables");
            
            if(!dossier.exists()){
                dossier.mkdirs();
            }
            
            File file = new File("E:\\Tables\\"+wordRequest[2]+".txt");
            if(!file.exists()){
                file.createNewFile();
            }
            table = new Table();
            table.setNom(wordRequest[2]);

            String[] column = wordRequest[4].split(",");
            table.setColumnName(column);
        
            allTable.add(table);
            
            Writer write = new Writer();
            write.write("E:\\Tables\\"+wordRequest[2], column);
            
            
            res = "Creer";
            
        } catch(Exception ex){
            ex.printStackTrace();
        }
        
        return res;
    }
    
    /*******************************************Fonction insertion *****************************************************/
    public String insert(String request){
        String[] wordRequest = request.split(" ");
        String res = new String();
        Table table = new Table();
        table.setNom(wordRequest[2]);
        String[] values = wordRequest[4].split(",");
        System.out.println("reoo"+values[0]);
        Writer write = new Writer();
        write.write("E:\\Tables\\"+wordRequest[2],values);
        
        res = "Valeur inserer";
        
        return res;

    }
    
    /*************************************Fonction affichant tout resultat*********************************************************/
    public void print(Table table){
        for(int i = 0 ;i<table.getColumnValue().size();i++){
            String[] all = table.getColumnValue().get(i).split(",");
            for (int j = 0; j < all.length; j++) {
                System.out.print(all[j]+"\t");
            }
            System.out.print("\n");
           
        }
    }
    
    /******************************************Fonction affichant colonne specifique**************************************************************************/
    public Table projection(String request){
        String[] req = request.split(" ");
        Table table = new Table();
        table.setNom(req[3]);
        String[] name = req[1].split(",");
        //System.out.println("sarotra:"+table.getNom());
        ArrayList<String> allProject = new ArrayList<>();
        String[] valueCol = null;
        String str = "";

        Read read = new Read();
        ArrayList<String> array = read.read("E:\\Tables\\"+req[3]);
        //System.out.println("arrayname: "+array);
        String[] namecol = array.get(0).split(",");
        
        table.setColumnValue(array);
        table.setColumnName(namecol);
        //System.out.println("sarotra:"+table.getColumnName()[0]);

        for(int s= 0 ;s<table.getColumnValue().size();s++){
            valueCol = table.getColumnValue().get(s).split(",");
            
            for(int j = 0;j<namecol.length;j++){
                if(name.length>=2){
                    if(table.getColumnName()[j].equals(name[0])){
                        str = valueCol[j]+",";
                        //System.out.println(table.getColumnName()[j]);
                        //System.out.println("value voloany:"+str);
                    }
                    if(table.getColumnName()[j].equals(name[1])){
                        str = str+valueCol[j]+",";
                        allProject.add(str);
                        //System.out.println("vlue faharoa: "+str);
                    }
                }else if(name.length<2){
                    if(table.getColumnName()[j].equals(name[0])){
                        str = valueCol[j]+",";
                        allProject.add(str);
                        //System.out.println(valueCol[j]);
                    }
                }
            }
        } 

        Table tab = new Table();
        tab.setColumnValue(allProject);
        tab.setColumnName(name);
        tab.setNom(req[3]);
    
        return tab;
    }
    
    /****************************************Fonction affichant tout element table*****************************************************/
    
    public Table select(String request){
        
        String[] wordRequest = request.split(" ");
        Table table = new Table();
        //table.setNom(wordRequest[3]);
        Read read = new Read();
        ArrayList<String> readString = read.read("E:\\Tables\\"+wordRequest[3]);
        ArrayList<String> array = new ArrayList<>();
        String[] nameCol = readString.get(0).split(",");
        //System.out.println("ouaoooo: "+nameCol[0]);
        
        String[] value = null;
        String str = "";
        String[] val = null;
        table.setColumnValue(readString);
        table.setColumnName(nameCol);
        
        if(wordRequest.length<=4){
            return table;
            
        }else if(wordRequest.length>4){
            for(int a = 0;a<table.getColumnValue().size();a++){
            for(int i = 0;i<nameCol.length;i++){
                value = table.getColumnValue().get(a).split(",");
                for (int j = 0; j < value.length; j++) {
                    if(nameCol[i].equals(wordRequest[5]) &&  value[j].equals(wordRequest[7])){
                        //val = table.getColumnValue().get(a).split(",");
                        
                        for(int x = 0;x<value.length;x++){
                            str = value[x]+",";
                            array.add(str);
                        }
                        
                    }
                }
            }
            }
        }
        
        Table tab = new Table();
        tab.setColumnValue(array);
        tab.setColumnName(value);
        tab.setNom(wordRequest[3]);
        
        
        return tab;
        
    }
    
    /***************************************Produit cartesien**************************************/
    public Table produitCartesien(String request){
        String[] req = request.split(" ");
        String[] table = req[2].split(",");
        ArrayList<String> allPrdt = new ArrayList<>();
        ArrayList<String> tout = new ArrayList<>();
        ArrayList<String> arrayPers = null;
        ArrayList<String> arrayOlona = null;
        //System.out.println("table: "+table[1]);
        
        Table tabPers = new Table();
        Read readPers = new Read();
        arrayPers = readPers.read("E:\\Tables\\"+table[0]);
        tabPers.setColumnValue(arrayPers);
        //System.out.println("table: "+arrayPers);
        
        
        Table tabOlona = new Table();
        Read readOlona = new Read();
        arrayOlona = readOlona.read("E:\\Tables\\"+table[1]);
        tabOlona.setColumnValue(arrayOlona);
        //System.out.println("kalolo: "+arrayOlona);
        
        for(int i = 1;i<arrayPers.size();i++){
            for(int j = 1;j<arrayOlona.size();j++){
                allPrdt.add(arrayPers.get(i)+arrayOlona.get(j));
            }
        }

        Table tab = new Table();
        tab.setColumnValue(allPrdt);
       
        tab.setColumnName(table);

        return tab;
    }
    
    
    /***************************Jointure*****************************/
    
     public Table jointure(String request){
        String[] req = request.split(" ");
        ArrayList<String> allJoin = new ArrayList<>();

        Table tabPers = new Table();
        Read readPers = new Read();
        ArrayList<String> arrayPers = readPers.read("E:\\Tables\\"+req[3]);
        tabPers.setColumnValue(arrayPers);
        System.out.println("nataony"+arrayPers);

        Table tabOlona = new Table();
        Read readOlona = new Read();
        ArrayList<String> arrayOlona = readOlona.read("E:\\Tables\\"+req[5]);
        tabOlona.setColumnValue(arrayOlona);
        
        for(int i = 0;i<tabOlona.getColumnValue().size();i++){
            for(int j =0;j<tabPers.getColumnValue().size();j++){
                String[] valuesOlona =tabOlona.getColumnValue().get(i).split(",");
                String[] valuesPers = tabPers.getColumnValue().get(j).split(",");

                if(valuesOlona[0].equals(valuesPers[0])){
                    allJoin.add(tabPers.getColumnValue().get(j)+tabOlona.getColumnValue().get(i));
                }
            }
        }
        
        Table tab = new Table();
        tab.setColumnValue(allJoin);
        //tab.setColumnName(req);
        
        return tab;

    }
     
    /**************************Intersection**********************************/
        public Table intersection(String request){
        String[] req = request.split(" ");
        String[] table = req[2].split(",");
        ArrayList<String> allInter =  new ArrayList<>();

        Table tabPers = new Table();
        Read readPers = new Read();
        ArrayList<String> arrayPers = readPers.read("E:\\Tables\\"+table[0]);
        tabPers.setColumnValue(arrayPers);

        Table tabOlona = new Table();
        Read readOlona = new Read();
        ArrayList<String> arrayOlona = readOlona.read("E:\\Tables\\"+table[1]);
        tabOlona.setColumnValue(arrayOlona);
        //System.out.println("arrayOlona"+table[1]);

        for(int i = 1;i<arrayPers.size();i++){
            if(arrayOlona.contains(arrayPers.get(i))){
                allInter.add(arrayPers.get(i));
                
            }else{
                //System.out.println(arrayPers.get(i)+"============"+arrayOlona);
            }
        }

        Table tab = new Table();
        tab.setColumnValue(allInter);
        
        return tab;

    }
}
