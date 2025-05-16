import java.util.Scanner;

public class AddCommand implements Command {
    private WarehouseService service;
    private Scanner scanner;

    public AddCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Име: ");
        String name = scanner.nextLine();

        System.out.print("Срок (YYYY-MM-DD): ");
        String expiry = scanner.nextLine();

        System.out.print("Дата на постъпване: ");
        String arrival = scanner.nextLine();

        System.out.print("Производител: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Единица (kg/l): ");
        String unit = scanner.nextLine();

        System.out.print("Количество: ");
        double quantity = Double.parseDouble(scanner.nextLine());

        Location location = null;
        while (true) {
            System.out.print("Местоположение (секция/рафт/номер): ");
            String[] loc = scanner.nextLine().split("/");
            location = new Location(loc[0], loc[1], loc[2]);

            if (service.isLocationOccupied(location)) {
                System.out.println("Местоположението " + location + " вече е заето! Моля, опитайте друго.");
            } else {
                break;
            }
        }

        System.out.print("Коментар: ");
        String comment = scanner.nextLine();

        Product p = new Product(name, expiry, arrival, manufacturer, unit, quantity, location, comment);
        service.addProduct(p);
        System.out.println("Добавен продукт.");
    }
}
