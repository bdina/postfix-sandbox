package com.dyndns.email.postfix.milter;

import java.io.InputStreamReader;

import javax.mail.MimeMessage;
import javax.mail.Session;

public class PostfixMilter {
    public static void main ( String [] args ) throws Exception {
        final BufferedReader stdin = new InputStreamReader( System.in );

        final Message message = parseMessage ( stdin );

        stdin_reader.close ();
    }

    private static MimeMessage parseMessage ( final InputStream in_stream ) {
        final Session mail_session = Session.getDefaultInstance( new Properties () );
        final MimeMessage message = new Message ( mail_session, in_stream );
        return message;
    }
}
