/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]){
        HostBlackListsValidatorThread hblvThread= new HostBlackListsValidatorThread("200.24.34.55", 5);  
        List<Integer> blackListOcurrences=hblvThread.getBlackListOcurrencesThread();
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
        //HostBlackListsValidatorThread hblvThread2= new HostBlackListsValidatorThread("212.24.24.55", 5);  
        //List<Integer> blackListOcurrences2=hblvThread2.getBlackListOcurrencesThread();
        //System.out.println("The host was found in the following blacklists:"+blackListOcurrences2);
        
        //HostBlackListsValidator hblv=new HostBlackListsValidator();
        //List<Integer> blackListOcurrences=blackListOcurrencesThread;
        //System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
    }
    
}
