package ru.stqa.pft.soap.tests;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.115.245.53");
    Assert.assertEquals(geoIP.getCountryCode(), "UKR");
  }
}
