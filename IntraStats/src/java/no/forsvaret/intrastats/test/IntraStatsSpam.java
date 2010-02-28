/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <eirikdb@gmail.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Eirik Brandtzæg
 * ----------------------------------------------------------------------------
 */
package no.forsvaret.intrastats.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * HOTOWO RUN (for instance)
 * target/classes$ java no/forsvaret/intrastats/test/IntraStatsSpam
 *
 * @author Eirik Brandtzæg <eirikdb@gmail.com>
 */
public class IntraStatsSpam {

    public static void main(String[] args) {
        final int maxTimes = 100;
        final int maxThreads = 100;
        for (int i = 0; i < maxThreads; i++) {
            final int threadCount = i;
            new Thread() {

                @Override
                public void run() {
                    try {
                        for (int count = 0; count < maxTimes; count++) {
                            long time = System.currentTimeMillis();
                            String u = "http://localhost:8080/IntraStats/pageVisit/index?"
                                    + "jsoncallback=1234"
                                    + "&url=http://www.google" + (count * maxTimes + threadCount) + ".com"
                                    + "&section=Test" + threadCount;
                            URL url = new URL(u);
                            URLConnection con = url.openConnection();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(threadCount + " - " + count + " - " + (System.currentTimeMillis() - time) + " - " + line);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(IntraStatsSpam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
    }
}
