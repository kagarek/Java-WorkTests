package pdf;import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Igor_Makarychev on 25.11.2015.
 */
public class PDFBox_lib {

    public static void main(String[] args) throws IOException {
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File("D:\\Scans_20160330083511.pdf");
        try {
            PDFParser parser = new PDFParser(new FileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            //pdfStripper.setEndPage(5);
            pdfStripper.setEndPage(1);
            String parsedText = pdfStripper.getText(pdDoc);
            String[] lines = parsedText.split("\r\n");

            processLines(lines);

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processLines(String[] lines)
    {
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i=0; i < lines.length;i++) {
            String line = lines[i];
            if (!line.equals("(FK)") && !line.startsWith("Page")) {
                arrayList.add(line);
            }
        }

        String a = "org_id org_id_ud NOT NULL";

        System.out.println();
    }

}
