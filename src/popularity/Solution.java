package popularity;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Created by igormakarychev on 10/5/17.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(calculatePopularity(50,1000,0.05));
        System.out.println(calculatePopularity(250,1000,0.05));
        System.out.println(calculatePopularity(500,1000,0.05));

        System.out.println(calculatePopularity(500,1000,0.05)*365/1);
        System.out.println(calculatePopularity(500,1000,0.05)*365/180);
        System.out.println(calculatePopularity(500,1000,0.05)*365/365);
    }

    public static double calculatePopularity(int POE, int TOE, double conf){
        if (TOE == 0) return 0;

        double mean = 0.05D;
        double standardDev = 0.05;

        NormalDistribution distribution = new NormalDistribution(mean, standardDev);
        double z = distribution.inverseCumulativeProbability(1-(1-conf)/2);

        double phat = 1.0*POE/TOE;

        return (phat + z*z/(2*TOE) - z * Math.sqrt((phat*(1-phat)+z*z/(4*TOE))/TOE))/(1+z*z/TOE);
    }
}