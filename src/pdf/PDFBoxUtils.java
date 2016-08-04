package pdf;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.Splitter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Makarychev on 8/4/2016.
 */
public class PDFBoxUtils {

    public static void main(String[] args) {
//        List<File> filesToMerge = new ArrayList<>();
//        filesToMerge.add(new File("/Users/igormakarychev/Dropbox/docs/scans/local passports/I1.pdf"));
//        filesToMerge.add(new File("/Users/igormakarychev/Dropbox/docs/scans/local passports/I2.pdf"));
//        filesToMerge.add(new File("/Users/igormakarychev/Dropbox/docs/scans/local passports/I3.pdf"));
//        filesToMerge.add(new File("/Users/igormakarychev/Dropbox/docs/scans/local passports/I4.pdf"));
//        filesToMerge.add(new File("/Users/igormakarychev/Dropbox/docs/scans/local passports/I5.pdf"));
//        mergePDFs(filesToMerge,"/Users/igormakarychev/Dropbox/docs/scans/local passports/Local Passport - Makarychev.pdf");

        //splitPDFtoPages(new File("/Users/igormakarychev/Dropbox/docs/scans/local passports/Local Passport - Makarychev.pdf"));

        List<String> imagesToConvert = new ArrayList<>();

        imagesToConvert.add("/Users/igormakarychev/Dropbox/docs/scans/other documents/Birth certificate - Max.jpg");
        imagesToPDF(imagesToConvert,"/Users/igormakarychev/Dropbox/docs/scans/other documents/Birth certificate - Max.pdf");

        imagesToConvert.clear();

        imagesToConvert.add("/Users/igormakarychev/Dropbox/docs/scans/other documents/Martial certificate.jpg");
        imagesToPDF(imagesToConvert,"/Users/igormakarychev/Dropbox/docs/scans/other documents/Martial certificate.pdf");
    }

    public static void splitPDFtoPages (File file) {
        try {
            PDDocument document = PDDocument.load(file);
            Splitter splitter = new Splitter();
            //splitter.setStartPage(2);
            //splitter.setEndPage(5);
            List<PDDocument> splittedDocuments = splitter.split(document);

            for (int i = 0; i < splittedDocuments.size(); i++) {
                splittedDocuments.get(i).save(new File(file.getPath() + " - " + i + ".pdf"));
                splittedDocuments.get(i).close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
    }

    public static void mergePDFs(List<File> filesToMerge, String destinationFileName){
        try {
            PDFMergerUtility ut = new PDFMergerUtility();
            PDDocument resultDoc = new PDDocument();

            for (int i = 0; i < filesToMerge.size(); i++) {
                PDDocument document = PDDocument.load(filesToMerge.get(i));
                Splitter splitter = new Splitter();
                //splitter.setStartPage(2);
                //splitter.setEndPage(5);
                List<PDDocument> splittedDocuments = splitter.split(document);

                for (PDDocument p : splittedDocuments) {ut.appendDocument(resultDoc, p);}
            }

            //ut.setDestinationFileName(destinationFileName);
            ut.mergeDocuments();
            resultDoc.save(destinationFileName);

            System.out.println(destinationFileName+" successfully created!");
        } catch (COSVisitorException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPDFText(File pdfFile){
        try {
            PDFParser parser = new PDFParser(new FileInputStream(pdfFile));
            parser.parse();

            COSDocument cosDoc = parser.getDocument();
            PDDocument pdDoc = new PDDocument(cosDoc);

            PDFTextStripper pdfStripper = new PDFTextStripper();

            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);

            return pdfStripper.getText(pdDoc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removePagesFromPDF(String existingFilePath, String cleanedFile, ArrayList<Integer> pagesToRemove){
        try {
            PDDocument full = PDDocument.load(new File(existingFilePath));
            PDDocument resDoc = new PDDocument();

            List<PDPage> fullPages = full.getDocumentCatalog().getAllPages();

            for (int p = 0; p < fullPages.size(); ++p) {
                if (!pagesToRemove.contains(p)) {
                    resDoc.addPage(fullPages.get(p));
                }
            }

            resDoc.save(new File(cleanedFile));
            full.close();
            resDoc.close();

            System.out.println(cleanedFile+" successfully created!");

        } catch (COSVisitorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void imagesToPDF(List<String> imageFileNames, String resultDoc){
        try {
            PDDocument document = new PDDocument();

            for (int i = 0; i < imageFileNames.size(); i++) {
                String[] fileNames = new File(imageFileNames.get(i)).list();

                for (int j = 0; j < fileNames.length; j++) {
                    String image = fileNames[j];
                    InputStream in = new FileInputStream(image);
                    BufferedImage bimg = ImageIO.read(in);
                    float width = bimg.getWidth();
                    float height = bimg.getHeight();
                    PDPage page = new PDPage(new PDRectangle(width, height));
                    document.addPage(page);
                    BufferedImage awtImage = ImageIO.read(new File(image));
                    PDXObjectImage img = new PDPixelMap(document, awtImage);
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.drawImage(img, 0, 0);
                    contentStream.close();
                    in.close();
                }
            }

            document.save(resultDoc);
            document.close();
        } catch (COSVisitorException|IOException e) {
            e.printStackTrace();
        }
    }

    public static void replacePagesinPDF(){
        try {
            PDDocument full = PDDocument.load(new File("/Users/igormakarychev/Downloads/Full.pdf"));
            PDDocument doc9 = PDDocument.load(new File("/Users/igormakarychev/Downloads/9.pdf"));
            PDDocument doc10 = PDDocument.load(new File("/Users/igormakarychev/Downloads/10.pdf"));
            PDDocument resDoc = new PDDocument();

            List<PDPage> fullPages = full.getDocumentCatalog().getAllPages();
            List<PDPage> doc9Pages = doc9.getDocumentCatalog().getAllPages();
            List<PDPage> doc10Pages = doc10.getDocumentCatalog().getAllPages();

            // replace the 3rd page of the 2nd file with the 1st page of the 1st one
            for (int p = 0; p < fullPages.size(); ++p) {
                if (p == 8)
                    resDoc.addPage(doc9Pages.get(0));
                else if (p == 9)
                    resDoc.addPage(doc10Pages.get(0));
                else
                    resDoc.addPage(fullPages.get(p));
            }

            resDoc.save(new File("/Users/igormakarychev/Downloads/Result.pdf"));
            full.close();
            doc9.close();
            doc10.close();
            resDoc.close();
        } catch (COSVisitorException | IOException e) {
            e.printStackTrace();
        }
    }
}
