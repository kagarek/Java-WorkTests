package excelToImage;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;

/**
 * Created by Igor_Makarychev on 18.03.2016.
 */
public class Solution {

    public static void main(String[] args) throws Exception {
        //Instantiate a new workbook with path to an Excel file
        Workbook book = new Workbook("Excel-to-image\\src\\main\\resources\\test_excel.xlsx");

        //Save the document in Pdf format
        book.save("Excel-to-image\\src\\main\\resources\\Excel_Saved_as_PDF.pdf", FileFormatType.PDF);

        //Create an object for ImageOptions
        /*ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
        //Set the image type
        imgOptions.setImageFormat(ImageFormat.getPng());
        //Get the first worksheet.

        int i=0;

        while (true)
        {
            try {
                Worksheet sheet = book.getWorksheets().get(i);

                //Create a SheetRender object for the target sheet
                SheetRender sr = new SheetRender(sheet, imgOptions);
                for (int j = 0; j < sr.getPageCount(); j++) {
                    //Generate an image for the worksheet
                    //sr.toImage(j, "Excel-to-image\\src\\main\\resources\\mysheetimg_" + j + ".png");
                    sr.toImage(j, book.getFileName() + "_" + sheet.getName()+ "_" + j + ".jpg");
                }
                i++;
            }
            catch (IndexOutOfBoundsException ix)
            {
                System.out.println(ix.getCause());
                break;
            }
        }*/
    }

}
