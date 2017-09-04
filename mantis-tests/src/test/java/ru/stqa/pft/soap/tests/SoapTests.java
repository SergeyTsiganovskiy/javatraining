package ru.stqa.pft.soap.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.soap.model.Issue;
import ru.stqa.pft.soap.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SoapTests extends TestBase{

  @Test
  public void testGetProgects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProject();
    System.out.println(projects.size());
    for (Project project:projects){
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException{
    Set<Project> projects = app.soap().getProject();
    Issue issue = new Issue().withSummary("Test summary")
            .withDescription("Test description").withProject(projects.iterator().next());
    Issue created =  app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testIssueStatus() throws MalformedURLException, ServiceException, RemoteException{
    skipIfNotFixed(1);
  }

  @Test
  public void testIssueStatusRest() throws IOException {
    skipIfNotFixedForRest(41);
  }
}
