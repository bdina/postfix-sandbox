package com.dyndns.email.postfix.milter;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.mail.Session;

public class PostfixMilter {
    public static void main ( String [] args ) throws Exception {
        final InputStream stdin = System.in;
        final MimeMessage message = parseMessage ( stdin );

        stdin.close ();
    }

    private static MimeMessage parseMessage ( final InputStream in_stream )
            throws Exception {
        final Session mail_session = Session.getDefaultInstance ( new Properties () );
        final MimeMessage message = new MimeMessage ( mail_session, in_stream );
        return message;
    }
}
