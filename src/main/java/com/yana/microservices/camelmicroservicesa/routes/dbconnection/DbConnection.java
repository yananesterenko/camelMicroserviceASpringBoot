package com.yana.microservices.camelmicroservicesa.routes.dbconnection;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbConnection {



    @Autowired
    JpaConfig jpaConfig;

    @Autowired
    XmlValidator xmlValidator;


    public void connection() {
        System.out.println("test connect");

        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//        BasicDataSource basic = new BasicDataSource();
//        basic.setDriverClassName("cdata.jdbc.postgresql.PostgreSQLDriver");
//        //basic.setUrl("jdbc:oracleoci:User=myuser;Password=mypassword;Server=localhost;Port=1521;");
//        basic.setUrl("jdbc:postgresql:User=" + user + ";Password=" + password + ";Database=testdb;Server=127.0.0.1;Port=5432;");
//        DataSource dataSource = new DriverManagerDataSource(
//                "jdbc:postgresql://localhost:5432/testdb?user=postgres&password=postgres");

        DataSource dataSource= jpaConfig.dataSource();

        context(dataSource);
    }

    public void context(DataSource dataSource) {

        CamelContext context = new DefaultCamelContext();

        context.getRegistry().bind("myTestDb", dataSource);


        //      XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
//        xmlJsonFormat.setEncoding("UTF-8");
//        xmlJsonFormat.setForceTopLevelObject(true);
//        xmlJsonFormat.setTrimSpaces(true);
//        xmlJsonFormat.setRootName("newRoot");
//        xmlJsonFormat.setSkipNamespaces(true);
//        xmlJsonFormat.setRemoveNamespacePrefixes(true);
//        xmlJsonFormat.setExpandableProperties(Arrays.asList("d", "e"));

        String select_temp = "select id as CUSTOMER_CODE, name as COMPANY_NAME, count as my_count from documents as my_document where id > :?key";
        String select1 = "SELECT jde.id, payment_terms, description, mpd.address_number" +
                "FROM public.jde_payment_terms as jde inner join  mpd_so_customers as mpd on jde.fk_customer_id=mpd.id";
        String select = "SELECT id, payment_terms, description  FROM public.jde_payment_terms ";



        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("timer://foo?period=60000")
                            .routeId("JDBS Route")
                            .setHeader("key", constant(1))
                            .setBody(simple(select))
                            //.setBody(constant("select * from Account"))
                            .to("jdbc:myTestDb?useHeadersAsParameters=true")
                            .marshal().json()
                            //.marshal()
                            .log(">> ${body}")
                            .to("file:C:\\Projects\\Json\\Documents?fileName=account_jsn.json")
                            //.to("xj:identity?transformDirection=JSON2XML")
                            .to("xj:file:files/Gore_json2xml.xsl?transformDirection=JSON2XML")
                            //.log(">> ${body}")
                            .to("file:C:\\Projects\\Json\\Documents?fileName=account1.xml")
                            //.marshal(xmlJsonFormat)
                            .to("log:?level=INFO&showBody=true");
                        try {
                            XmlValidation2.validate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                           validator()
//                            .type("xml:ABCOrder")
//                            .withUri("validator:file:files/input?fileName=schema.xsd");

//                    validator()
//                            .type("json")
//                            .withJava(com.example.MyCustomValidator.class);

//
//                    from("file:C:\\Projects\\Json\\Documents?fileName=account.json")
//                           // .convertBodyTo(String.class)
//                            .unmarshal().jacksonxml(Jackson)
//
//                            .log(">> ${body}");

//                    from("file:files/input?fileName=account.json")
//                            .log(">> ${body}")
//                            .to("xj:identity?transformDirection=JSON2XML")
//                            .log(">> ${body}");;

                }

            });
            context.start();
            Thread.sleep(10000);
            context.stop();

           // xmlValidator.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



