/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datereminder.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author namro_000
 */
public class SystemPreferences {
    private final Properties prop;
    private final String file = "config.properties";
    private final File config;
    
    public SystemPreferences(){
        prop = new Properties();    
        config = new File(file);
        try {
            loadProperties();
        } catch (IOException ex) {
            Logger.getLogger(SystemPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    private void loadProperties() throws IOException{
        InputStream in = null;
        try {       
            in = new FileInputStream(file);
            prop.load(in);
        } catch (FileNotFoundException ex) {
            if(config.createNewFile()){
                set("completion", 120);
                set("delivery", 60);
                set("requisition", 21);
                set("commitment", 45);
                set("req_withdrawal", 7);
                set("damage_cancellation", 14);
            }            
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(SystemPreferences.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(SystemPreferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int get(String property){
        try{
        return Integer.valueOf(prop.getProperty(property));
        }catch(NullPointerException ex){
            return 0;
        }        
    }   
    
    public void set(String property,int days){
        OutputStream out = null;
        try {
            out = new FileOutputStream(config);
            prop.setProperty(property, String.valueOf(days));
            prop.store(out, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemPreferences.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SystemPreferences.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SystemPreferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    
    
    public static void main(String[] args) {
        SystemPreferences sys = new SystemPreferences();
    }
}
