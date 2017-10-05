package localesGetter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Created by igormakarychev on 1/10/17.
 */
public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("/Users/igormakarychev/Documents/Java projects/Java-WorkTests/resources/countries.txt"));

        String s = null;
        TreeSet<String> treeSet = new TreeSet<>();

        TreeSet<String> treeSetResult = new TreeSet<>();

        while ((s = br.readLine()) != null)
            treeSet.add(s.toUpperCase());

        HashMap<String,String> map = new HashMap<>();

        for (Locale l : Locale.getAvailableLocales())
            if (!l.getCountry().equals("") && treeSet.contains(l.getCountry()))
                treeSetResult.add(l.getCountry() +" - "+ l.toString());

        for (String r : treeSetResult)
            System.out.println(r);
    }
}
