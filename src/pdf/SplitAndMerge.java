package pdf;import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.Splitter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Igor_Makarychev on 30.03.2016.
 */
public class SplitAndMerge {
    public static void main(String[] args){
        File file = new File("D:\\Scans_20160330083511.pdf");
        try {

            PDDocument document = PDDocument.load(file);
            Splitter splitter = new Splitter();
            splitter.setStartPage(2);
            splitter.setEndPage(5);
            List<PDDocument> splittedDocuments = splitter.split(document);

            PDFMergerUtility ut = new PDFMergerUtility();
            PDDocument resultDoc = new PDDocument();

            for (PDDocument p : splittedDocuments) {

                ut.appendDocument(resultDoc,p);
            }

            ut.setDestinationFileName("D:\\Passport.pdf");
            ut.mergeDocuments();

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
    }
}
