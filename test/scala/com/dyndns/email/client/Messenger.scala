package com.dyndns.email.client

import java.util.Properties
 
import javax.mail._
import javax.mail.internet._

object Messenger extends App {
  override def main(args: Array[String]) {
    val smtp_host = args(0)
    val username = args(1)
    val password = args(2)
    val message_count = Integer.valueOf(args(3))

    val properties = new Properties()
    properties.put("mail.smtp.host",        smtp_host)
    properties.put("mail.smtp.port",        "25")
    properties.put("mail.smtp.auth",        "true")
    properties.put("mail.smtp.sasl.enable", "true")

    val session = Session.getInstance(properties)
    val transport = session.getTransport("smtp")

    transport.connect(username, password)

    val message = new MimeMessage(session)
    message.setSubject("Testing")
    message.setFrom(new InternetAddress(s"$username@postfix.local"))
    message.setRecipient(Message.RecipientType.TO, new InternetAddress("test@smtpsink.local"))
    message.setText("This is a test")

    for ( _ <- 0 to message_count) {
      transport.sendMessage(message, message.getAllRecipients())
    }

    transport.close()
  }
}
