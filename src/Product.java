class Product {
    private String name;
    private LocalDate expirationDate;
    private LocalDate arrivalDate;
    private String manufacturer;
    private String measurementUnit;
    private double quantity;
    private String location;
    private String comment;

    public Product(String name, LocalDate expirationDate, LocalDate arrivalDate,
                   String manufacturer, String measurementUnit, double quantity,
                   String location, String comment) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.arrivalDate = arrivalDate;
        this.manufacturer = manufacturer;
        this.measurementUnit = measurementUnit;
        this.quantity = quantity;
        this.location = location;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }

    public String getComment() {
        return comment;
    }
