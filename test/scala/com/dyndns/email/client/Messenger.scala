package com.dyndns.email.client

import java.util.Properties
 
import javax.mail._
import javax.mail.internet._

object Messenger extends App {
  val properties = new Properties()
  properties.put("mail.smtp.host", "postfix.local")
  properties.put("mail.smtp.port", "25")

  val session = Session.getInstance(properties)

  val message = new MimeMessage(session)
  message.setSubject("Testing")
  message.setFrom(new InternetAddress("test@postfix.local"))
  message.setRecipient(Message.RecipientType.TO, new InternetAddress("test@smtpsink.local"))
  message.setText("This is a test")

  Transport.send(message)
}
