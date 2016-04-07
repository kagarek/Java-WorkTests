package pdf;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by igormakarychev on 4/7/16.
 */
public class ReplacePages {

    public static void main(String[] args) throws IOException, COSVisitorException {
        PDDocument full = PDDocument.load(new File("/Users/igormakarychev/Downloads/Full.pdf"));
        PDDocument doc9 = PDDocument.load(new File("/Users/igormakarychev/Downloads/9.pdf"));
        PDDocument doc10 = PDDocument.load(new File("/Users/igormakarychev/Downloads/10.pdf"));
        PDDocument resDoc = new PDDocument();

        List<PDPage> fullPages = full.getDocumentCatalog().getAllPages();
        List<PDPage> doc9Pages = doc9.getDocumentCatalog().getAllPages();
        List<PDPage> doc10Pages = doc10.getDocumentCatalog().getAllPages();

        // replace the 3rd page of the 2nd file with the 1st page of the 1st one
        for (int p = 0; p < fullPages.size(); ++p)
        {
            if (p == 8)
                resDoc.addPage(doc9Pages.get(0));
            else
            if (p == 9)
                resDoc.addPage(doc10Pages.get(0));
            else
                resDoc.addPage(fullPages.get(p));
        }

        resDoc.save(new File("/Users/igormakarychev/Downloads/Result.pdf"));
        full.close();
        doc9.close();
        doc10.close();
        resDoc.close();
    }
}
