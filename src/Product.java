
/**
 * Клас, представляващ продукт в склада.
 * Съдържа информация като име, дати, производител, количество и местоположение.
 */
public class Product {
    String name;
    String expiryDate;
    String arrivalDate;
    String manufacturer;
    String unit;
    double quantity;
    Location location;
    String comment;

    /**
     * Създава инстанция на Product с пълна информация за продукта.
     */
    public Product(String name, String expiryDate, String arrivalDate, String manufacturer,
                   String unit, double quantity, Location location, String comment) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.arrivalDate = arrivalDate;
        this.manufacturer = manufacturer;
        this.unit = unit;
        this.quantity = quantity;
        this.location = location;
        this.comment = comment;
    }

    /**
     * Връща името на продукта.
     */
    public String getName() {
        return name;
    }

    /**
     * Връща количеството на продукта.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Връща текстово представяне на продукта.
     */
    public String toString() {
        return name + " (" + quantity + " " + unit + "), Производител: " + manufacturer +
               ", Срок: " + expiryDate + ", Местоположение: " + location;
    }
}
