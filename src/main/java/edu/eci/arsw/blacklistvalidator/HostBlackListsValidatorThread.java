/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel.suarez-e
 */
public class HostBlackListsValidatorThread extends Thread {

    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
    private static final int BLACK_LIST_ALARM_COUNT = 5;
    LinkedList<Integer> blackListOcurrencesThread = new LinkedList<>();
    HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
    int ocurrencesCount = 0;
    int checkedListsCount = 0;
    String ipaddress;
    int n;
    int init;
    int end;

    public HostBlackListsValidatorThread(String ipaddress, int n) {
        this.ipaddress = ipaddress;
        this.n = n;
        for (int j = 1; j <= n; j++) {
            Thread hilo = new Thread();
        }
        run();
    }

    @Override
    public void run() {
        for (int i = 0; i < skds.getRegisteredServersCount() && ocurrencesCount < BLACK_LIST_ALARM_COUNT; i++) {
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)) {
                ocurrence(i);
            }
        }
        if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddress);
        } else {
            skds.reportAsTrustworthy(ipaddress);
        }

        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
    }

    

    private void ocurrence(int i) {
        blackListOcurrencesThread.add(i);
        ocurrencesCount++;
    }
    
    public LinkedList<Integer> getBlackListOcurrencesThread(){
        return this.blackListOcurrencesThread;
    }
}
