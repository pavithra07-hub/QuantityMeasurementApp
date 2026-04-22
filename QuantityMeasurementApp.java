public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {

            // Step 1: Check same reference
            if (this == obj) {
                return true;
            }

            // Step 2: Check null or different class
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Step 3: Type casting
            Feet other = (Feet) obj;

            // Step 4: Compare double values safely
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        boolean result = f1.equals(f2);

        System.out.println("Equal: " + result);
    }
}