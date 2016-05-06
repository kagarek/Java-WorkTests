package pdf;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by igormakarychev on 5/2/16.
 */
public class RemovePages {

    public static void main(String[] args) throws IOException, COSVisitorException {
        PDDocument full = PDDocument.load(new File("/Users/igormakarychev/Dropbox/New appartments/Korizza/АИ 50 лет от 21.07.2015 copy.pdf"));
        PDDocument resDoc = new PDDocument();

        List<PDPage> fullPages = full.getDocumentCatalog().getAllPages();

        for (int p = 0; p < fullPages.size(); ++p)
        {
            if (p == 7 || p == 12 || p == 23) {resDoc.addPage(fullPages.get(p));}

        }

        resDoc.save(new File("/Users/igormakarychev/Downloads/Kitchen.pdf"));
        full.close();
        resDoc.close();
    }
}
