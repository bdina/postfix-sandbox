package com.dyndns.email.postfix.milter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PostfixMilter {
    public static void main(String [] args) throws Exception {
        final BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = r.readLine()) != null) {
            System.out.println(s);
        }

        r.close();
    }
}
