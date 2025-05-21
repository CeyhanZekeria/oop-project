import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

/**
 * Команда за добавяне на нов продукт в склада.
 * Извършва пълна валидация на въведените данни и проверява за съвпадения
 * на име и срок на годност. Позволява добавяне към съществуващи партиди
 * или създаване на нови при различен срок.
 */
public class AddCommand implements Command {
    private WarehouseService service;
    private Scanner scanner;

    public AddCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Проверява дали низът съдържа цифри.
     */
    private boolean containsDigit(String input) {
        return input.matches(".*\\d.*");
    }

    /**
     * Проверява дали даден низ е валидна дата.
     */
    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Изпълнява логиката за добавяне на продукт:
     * валидира входа, проверява за партиди със същото име и срок,
     * изисква местоположение и добавя продукта в склада.
     */
    @Override
    public void execute() {
        String name;
        while (true) {
            System.out.print("Име: ");
            name = scanner.nextLine().trim();
            if (!containsDigit(name)) break;
            System.out.println("Името не трябва да съдържа цифри!");
        }

        String expiry;
        while (true) {
            System.out.print("Срок на годност (YYYY-MM-DD): ");
            expiry = scanner.nextLine().trim();
            if (isValidDate(expiry)) break;
            System.out.println("Невалиден формат за дата!");
        }

        String arrival;
        while (true) {
            System.out.print("Дата на постъпване (YYYY-MM-DD): ");
            arrival = scanner.nextLine().trim();
            if (isValidDate(arrival)) break;
            System.out.println("Невалиден формат за дата!");
        }

        String manufacturer;
        while (true) {
            System.out.print("Производител: ");
            manufacturer = scanner.nextLine().trim();
            if (!containsDigit(manufacturer)) break;
            System.out.println("Производителят не трябва да съдържа цифри!");
        }

        String unit;
        while (true) {
            System.out.print("Единица (kg/l): ");
            unit = scanner.nextLine().trim();
            if (!containsDigit(unit)) break;
            System.out.println("Мерната единица не трябва да съдържа цифри!");
        }

        double quantity;
        while (true) {
            System.out.print("Количество: ");
            try {
                quantity = Double.parseDouble(scanner.nextLine().trim());
                if (quantity > 0) break;
                System.out.println("Количество трябва да е положително число!");
            } catch (NumberFormatException e) {
                System.out.println("Моля, въведете валидно число!");
            }
        }

        Location location = null;
        boolean validLocation = false;

        List<Product> sameNameProducts = service.findProductsByName(name);
        boolean sameExpiryExists = false;
        boolean differentExpiryExists = false;

        for (Product p : sameNameProducts) {
            if (p.expiryDate.equals(expiry)) sameExpiryExists = true;
            else differentExpiryExists = true;
        }

        while (!validLocation) {
            System.out.print("Местоположение (секция/рафт/номер): ");
            String[] loc = scanner.nextLine().split("/");
            if (loc.length != 3) {
                System.out.println("Местоположението трябва да е във формат секция/рафт/номер");
                continue;
            }
            location = new Location(loc[0], loc[1], loc[2]);

            if (service.isLocationOccupied(location)) {
                System.out.println("Местоположението " + location + " е заето!");
            } else {
                validLocation = true;
            }
        }

        String comment;
        while (true) {
            System.out.print("Коментар: ");
            comment = scanner.nextLine().trim();
            if (!containsDigit(comment)) break;
            System.out.println("Коментарът не трябва да съдържа числа!");
        }

        Product product = new Product(name, expiry, arrival, manufacturer, unit, quantity, location, comment);
        service.addProduct(product);
        System.out.println("Продуктът беше добавен успешно.");
    }
}
