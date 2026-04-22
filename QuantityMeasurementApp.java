public class QuantityMeasurementApp {

    // Base unit: FEET
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ✅ UC6: Addition (instance method)
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            // Convert both to base unit
            double sumFeet = this.toFeet() + other.toFeet();

            // Convert back to unit of first operand
            double resultValue = this.unit.fromFeet(sumFeet);

            return new Quantity(resultValue, this.unit);
        }

        // Optional static method
        public static Quantity add(Quantity q1, Quantity q2) {
            return q1.add(q2);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println(new Quantity(1.0, LengthUnit.FEET)
                .add(new Quantity(2.0, LengthUnit.FEET))); // 3 FEET

        System.out.println(new Quantity(1.0, LengthUnit.FEET)
                .add(new Quantity(12.0, LengthUnit.INCH))); // 2 FEET

        System.out.println(new Quantity(12.0, LengthUnit.INCH)
                .add(new Quantity(1.0, LengthUnit.FEET))); // 24 INCH

        System.out.println(new Quantity(1.0, LengthUnit.YARD)
                .add(new Quantity(3.0, LengthUnit.FEET))); // 2 YARD

        System.out.println(new Quantity(2.54, LengthUnit.CENTIMETER)
                .add(new Quantity(1.0, LengthUnit.INCH))); // ~5.08 CM
    }
}