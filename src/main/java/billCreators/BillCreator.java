package billCreators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import counters.PriceCounter;
import models.Item;
import org.apache.log4j.Logger;
import scanners.ConsoleHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class BillCreator {
    private static Scanner sc = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(BillCreator.class);
    private static float total = 0;


    public static void create() {

        Document document = new Document();


        byte ch;

        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src\\main\\resources\\config.properties"));
            PdfWriter.getInstance(document, new FileOutputStream(properties.getProperty("path")));

            LOGGER.info("Empty bill created");

            document.open();

            LOGGER.info("Bill was opened");


            System.out.println("To add item print '1', to get bill print '0'");

            ch = sc.nextByte();

            Paragraph paragraphHeader = new Paragraph(billHead());
            paragraphHeader.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphHeader);

            LOGGER.info("Head was added");


            document.add(billBody("product", "price", "amount"));

            while (ch != 0) {


                if (ch == 1) {
                    String name = ConsoleHandler.readName();
                    float itemPrice = ConsoleHandler.readPrice();
                    int amount = ConsoleHandler.readAmount();
                    float price = 0;

                    Item item = new Item(name, itemPrice);

                    price += PriceCounter.count(item, amount);

                    total += price;


                    document.add(billBody(item.getName(), String.valueOf(item.getPrice()), String.valueOf(amount)));
                    LOGGER.info("Body updated");

                } else {
                    System.out.println("Please enter valid value");
                }

                System.out.println("To add item print '1', to get bill print '0'");

                ch = sc.nextByte();

            }

            Paragraph paragraphFooter = new Paragraph(billFooter());
            paragraphFooter.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphFooter);

            LOGGER.info("Footer added");

            document.close();

            LOGGER.info("Document closed");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error(e);
        } catch (DocumentException e) {
            LOGGER.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }


    }


    private static String billHead() {
        StringBuffer result = new StringBuffer();

        result.append("WELCOME, TODAY ").append(new Date());
        return result.toString();
    }


    private static PdfPTable billBody(String name, String price, String amount) {
        PdfPTable pdfPTable = new PdfPTable(3);
        pdfPTable.addCell(name);
        pdfPTable.addCell(price);
        pdfPTable.addCell(amount);

        return pdfPTable;
    }


    private static String billFooter() {
        StringBuffer result = new StringBuffer();

        result.append("TOTAL: ").append(total);

        return result.toString();
    }


}
