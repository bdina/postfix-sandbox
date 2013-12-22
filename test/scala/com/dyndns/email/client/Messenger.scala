package com.dyndns.email.client

import java.util.Properties
 
import javax.mail._
import javax.mail.internet._

case class Options( smtp_host     : String
                  , username      : String
                  , password      : String
                  , message_count : Int )

object Messenger extends App {
  override def main ( args : Array[String] ) : Unit = {
    val options = getOptions( args )

    val session = Session.getInstance( getProperties( options.smtp_host ) )
    val message = getMessage( options.username , session )

    println( "Starting the send..." )
    for ( _ <- 0 to options.message_count ) {
      val message_sender = new Thread ( new Runnable {
        def run() {
          val transport = session.getTransport( "smtp" )
          transport.connect( options.username, options.password )
          transport.sendMessage( message, message.getAllRecipients() )
          transport.close()
        }
      })
      message_sender.start
    }
    println( "Sending complete." )
  }

  def getOptions ( args : Array[String] ) : Options = {
    val smtp_host     = args(0)
    val username      = args(1)
    val password      = args(2)
    val message_count = Integer.valueOf(args(3))

    Options ( smtp_host , username , password , message_count )
  }

  def getProperties ( smtp_host :String ) : Properties = {
    val properties = new Properties ()
    properties.put("mail.smtp.host",        smtp_host)
    properties.put("mail.smtp.port",        "25")
    properties.put("mail.smtp.auth",        "true")
    properties.put("mail.smtp.sasl.enable", "true")
    properties
  }

  def getMessage ( from_name : String , session : Session ) : MimeMessage = {
    val message = new MimeMessage ( session )
    message.setSubject( "Testing" )
    message.setFrom( new InternetAddress ( s"$from_name@postfix.local" ) )
    message.setRecipient( Message.RecipientType.TO 
                        , new InternetAddress( "test@smtpsink.local" ) )
    message.setText( "This is a test" )
    message
  }
}
