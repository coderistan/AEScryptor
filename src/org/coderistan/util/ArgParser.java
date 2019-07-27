package org.coderistan.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Argument parser by coderistan
 * demo version
 */

public class ArgParser {

    private Map<String, String> list;

    public ArgParser() {
        this.list = new HashMap<String, String>();
    }

    public boolean parse(String[] args) {
        try {
            for (String arg : args) {
                if(arg.equalsIgnoreCase("help")){
                    this.list.put("help","");
                    this.printHelp();
                    return true;
                }
                
                String[] current = arg.split("=");
                
                if (current.length != 2) {
                    return false;
                } else if (this.list.get(current[0]) == null) {
                    this.printError(arg);
                    return false;
                } else {
                    this.list.put(current[0], current[1]);
                }
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("?");
            return false;
        }
    }

    public String get(String key) {
        return this.list.get(key);
    }

    public void addOption(String name, String value) {
        this.list.put(name, value);
    }
    
    public void printError(String key){
        System.out.println("Hatalı argüman: "+key);
    }
    
    public void printHelp(){
        System.out.println("AESCryptor komut satırı argümanları:\n");
        for(Entry<String,String> s:this.list.entrySet()){
            if(!s.getKey().equalsIgnoreCase("help"))
                System.out.println(s.getKey()+": "+s.getValue());
        }
    }

}
