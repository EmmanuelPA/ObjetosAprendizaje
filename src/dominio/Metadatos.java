package dominio;

import java.io.Serializable;
import java.util.HashMap;

public class Metadatos implements Serializable{
    
    private HashMap<String, String> generales;
    private HashMap<String, String> ciclicos;
    private HashMap<String, String> tecnicos;

    
    public Metadatos(){
    generales = new HashMap<String,String>();
    generales.put("title", "");
    generales.put("description", "");
    generales.put("keyword", "");
    generales.put("coverage", "");
    
    tecnicos = new HashMap<String,String>();
    tecnicos.put("size", "");
    tecnicos.put("location", "");
    tecnicos.put("type", "");
    
    ciclicos = new HashMap<String,String>();
    ciclicos.put("version", "");
    ciclicos.put("status", "");
    ciclicos.put("rol", "");
    ciclicos.put("entity", "");
    ciclicos.put("date", "");
    
    }
       
    
    public HashMap<String, String> getCiclicos() {
        return ciclicos;
    }

    public void setCiclicos(HashMap<String, String> ciclicos) {
        this.ciclicos = ciclicos;
    }
    
    
    public HashMap<String,String> getGenerales(){
        return generales;
    }
     
    public HashMap<String,String> getTecnicos(){
        return tecnicos;
    }
    
     public void setGenerales(HashMap<String,String> generales){
        this.generales= generales;
    }
     
    public void getTecnicos(HashMap<String,String> tecnicos){
        this.tecnicos=tecnicos;
    }
    
    public void addMetadato(String key, String value){
        if(generales.containsKey(key)){
            generales.put(key, value);
        }else if(tecnicos.containsKey(key)){
            tecnicos.put(key, value);
        }else if(ciclicos.containsKey(key)){
            ciclicos.put(key, value);
        }
    }
    
   
}
