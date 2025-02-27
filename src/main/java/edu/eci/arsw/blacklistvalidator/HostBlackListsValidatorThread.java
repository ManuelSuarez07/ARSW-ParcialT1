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

    private static final int BLACK_LIST_ALARM_COUNT = 5;

    public HostBlackListsValidatorThread() {
    }

    List<Integer> checkHost(String ipaddress, int n) {
        for (int i = 1; i <= n; i++){
            Thread hilo = new Thread();
            System.out.println(hilo);
            hilo.start();
        }
        LinkedList<Integer> blackListOcurrencesThread = new LinkedList<>();
        int ocurrencesCount = 0;

        HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();

        int checkedListsCount = 0;

        for (int i = 0; i < skds.getRegisteredServersCount() && ocurrencesCount < BLACK_LIST_ALARM_COUNT; i++) {
            checkedListsCount++;

            if (skds.isInBlackListServer(i, ipaddress)) {

                blackListOcurrencesThread.add(i);

                ocurrencesCount++;
            }
        }

        if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddress);
        } else {
            skds.reportAsTrustworthy(ipaddress);
        }

        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
        return blackListOcurrencesThread;
    }

    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());

}
