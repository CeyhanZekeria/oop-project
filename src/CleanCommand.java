import java.time.LocalDate;
import java.util.Iterator;

/**
 * Команда за премахване на продукти със срок на годност, изтекъл до днешна дата включително.
 */
public class CleanCommand implements Command {
    private WarehouseService service;

    /**
     * Създава инстанция на CleanCommand с достъп до склада.
     */
    public CleanCommand(WarehouseService service) {
        this.service = service;
    }

    /**
     * Изпълнява командата за почистване.
     * Премахва всички продукти със срок на годност, който е преди или равен на текущата дата.
     */
    @Override
    public void execute() {
        LocalDate now = LocalDate.now();
        Iterator<Product> iterator = service.getProducts().iterator();
        int removedCount = 0;

        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (!p.getExpiryDate().isAfter(now)) {
                System.out.println("Премахнат продукт: " + p);
                iterator.remove();
                service.removeProduct(p);
                removedCount++;
            }
        }

        System.out.println("Общо премахнати продукти: " + removedCount);
    }
}
