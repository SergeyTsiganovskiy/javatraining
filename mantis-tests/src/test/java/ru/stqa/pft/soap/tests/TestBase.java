package ru.stqa.pft.soap.tests;

import biz.futureware.mantis.rpc.soap.client.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.soap.appmanager.ApplicationManager;
import ru.stqa.pft.soap.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  public static void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {

    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public static void skipIfNotFixedForRest(int issueId) throws IOException {

    if (isIssueOpenRest(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public static boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("mantis.soap.link")));
    IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    if (issue.getStatus().getName().equals("resolved")) {
      return true;
    }
    return false;
  }

  public static boolean isIssueOpenRest(int issueId) throws IOException {
    String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "")
            .execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);

    JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");

    String state_name = issues.get(0).getAsJsonObject().get("state_name").getAsString();

    if (state_name.equals("Resolved")) {
      return true;
    }
    return false;
  }

}

