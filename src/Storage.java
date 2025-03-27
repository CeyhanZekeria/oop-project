class Storage {
    private List<Product> products;
    private static Storage instance = null;

    private Storage() {
        products = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

