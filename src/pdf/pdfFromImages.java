package pdf;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by igormakarychev on 4/7/16.
 */
public class pdfFromImages {

    public static void main(String[] args) throws IOException, COSVisitorException {
        PDDocument document = new PDDocument();

        String[] fileNames = new File("/Users/igormakarychev/Downloads/processed_file_2016-04-07-20-36-32/processed_file_2016-04-07-20-36-32/").list();

        for (int i = 0; i < fileNames.length; i++) {
            String someImage = "/Users/igormakarychev/Downloads/processed_file_2016-04-07-20-36-32/processed_file_2016-04-07-20-36-32/"+fileNames[i];
            InputStream in = new FileInputStream(someImage);
            BufferedImage bimg = ImageIO.read(in);
            float width = bimg.getWidth();
            float height = bimg.getHeight();
            PDPage page = new PDPage(new PDRectangle(width, height));
            document.addPage(page);
            BufferedImage awtImage = ImageIO.read(new File(someImage));
            PDXObjectImage img = new PDPixelMap(document, awtImage);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(img, 0, 0);
            contentStream.close();
            in.close();
        }

        document.save("/Users/igormakarychev/Downloads/test.pdf");
        document.close();
    }

}
