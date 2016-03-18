import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Igor_Makarychev on 27.01.2016.
 */
public class Solution {
    public static void main(String[] args) throws Exception {

        //Workbook workbook = new Workbook("D:\\Unique SQL Statements from Activity Log from 2015.xlsx");
        Workbook workbook = new Workbook("D:\\Modules code.xlsx");
        Worksheet worksheet = workbook.getWorksheets().get(0);
        Cells cells = worksheet.getCells();
        int cols = worksheet.getCells().getMaxDataColumn()+1;
        int rows = worksheet.getCells().getMaxDataRow();
        Object dataTable [][] = worksheet.getCells().exportArray(0,0,rows,cols);

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 1; i < dataTable.length; i++) {
            //String sql = dataTable[i][2].toString()
            String sql = dataTable[i][0].toString()
                    .replace("."," ").replace("\""," ")
                    .replace("("," ").replace(")"," ")
                    .replace("["," ").replace("]"," ")
                    .replace("!"," ").replace(";"," ");

            if (sql.startsWith("'")) continue;

            while (true) {
                Matcher m = Pattern.compile("dbo.*?\\s").matcher(sql);
                if (m.find())
                {
                    strings.add(m.group().trim());
                    sql = sql.substring(m.start()+m.group().trim().length());
                }
                else break;
            }
        }

        ArrayList<String> clean_strings = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {
            if (!clean_strings.contains(strings.get(i)))
                clean_strings.add(strings.get(i));
        }

        Collections.sort(clean_strings);

        for (int i = 0; i < clean_strings.size(); i++) {
            System.out.println(clean_strings.get(i));
        }


    }
}
/*
public static void main(String[] args) throws Exception {

        String s1 = "some test string1";
        String s2 = "some test string 2";
        String s3 = "some test string  3";

        String s4 = "some test string 2";
        String s5 = "some test string  3";
        String s6 = "some test string1";

        String s7 = "some test 2 string";
        String s8 = "some test string  3";
        String s9 = "some test string1";

        HashMap<Integer,String> dataAccess = new HashMap<>();
        HashMap<Integer,String> dataSSRS_1 = new HashMap<>();
        HashMap<Integer,String> dataSSRS_2 = new HashMap<>();

        dataAccess.put(s1.hashCode(),s1);
        dataAccess.put(s2.hashCode(),s2);
        dataAccess.put(s3.hashCode(),s3);

        dataSSRS_1.put(s4.hashCode(),s4);
        dataSSRS_1.put(s5.hashCode(),s5);
        dataSSRS_1.put(s6.hashCode(),s6);

        dataSSRS_2.put(s7.hashCode(),s7);
        dataSSRS_2.put(s8.hashCode(),s8);
        dataSSRS_2.put(s9.hashCode(),s9);

        if (compareMaps(dataAccess,dataSSRS_1))
            System.out.println("dataAccess and dataSSRS_1 equal");
        else
            System.out.println("dataAccess and dataSSRS_1 NOT equal!!!");

        if (compareMaps(dataAccess,dataSSRS_2))
            System.out.println("dataAccess and dataSSRS_2 equal");
        else
            System.out.println("dataAccess and dataSSRS_2 NOT equal!!!");
                }

                    private static boolean compareMaps(HashMap<Integer, String> dataAccess, HashMap<Integer, String> dataSSRS) {
        return dataAccess.equals(dataSSRS);
    }

            */
