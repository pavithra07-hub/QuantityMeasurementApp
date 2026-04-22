public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        double sumBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBase(), other.toBase()) == 0;
    }
}