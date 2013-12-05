package com.dyndns.email.postfix.milter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PostfixMilter {
    public static void main ( String [] args ) throws Exception {
        final BufferedReader stdin_reader = new BufferedReader ( new InputStreamReader( System.in ) );

        String line_of_input;
        while ( ( line_of_input = stdin_reader.readLine() ) != null ) {
            System.out.println( line_of_input );
        }

        stdin_reader.close ();
    }
}
