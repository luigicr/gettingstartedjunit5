package patientintake;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BMICalculator {

  public static double calculateBMI(Integer inches, Integer pounds) {
     Double bmi = (double) (pounds * 703) / (inches * inches);
     double roundedToTwoPlaces = new BigDecimal(bmi)
             .setScale(2, RoundingMode.HALF_UP).doubleValue();
     return roundedToTwoPlaces;
  }
}
