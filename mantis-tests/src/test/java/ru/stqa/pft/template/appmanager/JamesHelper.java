package ru.stqa.pft.template.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.template.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {

  private ApplicationManager app;

  private TelnetClient telnet;
  private InputStream in;
  private PrintStream out;

  private Session mailSession;
  private Store store;
  private String mailServer;

  public JamesHelper(ApplicationManager app) {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }

  public boolean doesUserExist(String name){
    initTelnetSession();
    write("verify " + name);
    String result = readUntil("exist");
    closeTelnetSession();
    return result.trim().equals("User " + name + " exist");
  }

  public void createUser(String name, String password ){
    initTelnetSession();
    write("adduser " + name + " " + password);
    String result = readUntil("User " + name + " added");
    closeTelnetSession();
  }



  public void deleteUser(String name, String password ){
    initTelnetSession();
    write("deluser " + name + " " + password);
    String result = readUntil("User " + name + " deleted");
    closeTelnetSession();
  }



  private void initTelnetSession() {
    mailServer = app.getProperty("mailserver.host");
    int port = Integer.parseInt(app.getProperty("mailserver.port"));
    String login = app.getProperty("mailserver.adminlogin");
    String password = app.getProperty("mailserver.adminpassword");

    try {
      telnet.connect(mailServer, port);
      in = telnet.getInputStream();
    } catch (Exception e){
      e.printStackTrace();
    }

    readUntil("Login id:");
    write(login);
    readUntil("Password:");
    write(password);

    readUntil("Welcome " + login + ". Help for a list of command");
  }


  private String readUntil(String pattern) {
    try{
      char lastChar = pattern.charAt(pattern.length() - 1);
      StringBuffer sb = new StringBuffer();
      char ch = (char) in.read();
      while (true) {
        System.out.print(ch);
        sb.append(ch);
        if (ch == lastChar) {
          if (sb.toString().endsWith(pattern)){
            return sb.toString();
          }
        }
        ch = (char) in.read();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void write(String value) {
    try {
      out.println(value);
      out.flush();
      System.out.println(value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void closeTelnetSession() {
    write("quit");
  }

  public void drainEmail(String userName, String password) throws MessagingException{
    Folder inbox = openInbox(userName, password);
    for (Message message: inbox.getMessages()){
      message.setFlag(Flags.Flag.DELETED, true);
    }
    closeFolder(inbox);
  }

  private void closeFolder(Folder folder) throws MessagingException{
    folder.close(true);
    store.close();
  }

  private Folder openInbox(String userName, String password) throws MessagingException{
    store = mailSession.getStore("pop3");
    store.connect(mailServer, userName, password);
    Folder folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;
  }

  public List<MailMessage> waitForMessage(String userName, String password, long timeout)
          throws MessagingException, IOException {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout){
      List<MailMessage> allMail = getAllMail(userName, password);
      if (allMail.size() > 0){
        return allMail;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  private List<MailMessage> getAllMail(String userName, String password) throws MessagingException{
    Folder inbox = openInbox(userName, password);
    List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m)-> toModelMail(m)).collect(Collectors.toList());
    closeFolder(inbox);
    return messages;
  }

  public static MailMessage toModelMail(Message m){
    try{
      return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
    } catch (MessagingException e){
      e.printStackTrace();
      return null;
    } catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }

}

