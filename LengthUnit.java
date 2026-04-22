public class QuantityMeasurementApp {
    public static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        // Equality
        System.out.println("Equal? " + w1.equals(w2));

        // Conversion
        System.out.println("1 kg to pounds: " + w1.convertTo(WeightUnit.POUND));

        // Addition
        System.out.println("Add (kg): " + w1.add(w2));

        // Addition with target unit
        System.out.println("Add (grams): " + w1.add(w2, WeightUnit.GRAM));
    }
}