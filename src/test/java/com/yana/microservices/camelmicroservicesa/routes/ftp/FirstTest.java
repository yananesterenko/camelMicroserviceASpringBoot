package com.yana.microservices.camelmicroservicesa.routes.ftp;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class FirstTest extends CamelTestSupport {

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {
    return new RouteBuilder() {
      public void configure() throws Exception {     
        from("file://files/inbox")
          .to("file://files/outbox");
      }
    };
  }

  @Test
  public void testMoveFile() throws Exception {     
    template.sendBodyAndHeader("file:files/inbox", "Hello World",
                 Exchange.FILE_NAME, "hello.txt");
      Thread.sleep(2000);

      File target = new File("files/outbox/hello.txt");
      assertTrue("File not moved", target.exists());
}
}