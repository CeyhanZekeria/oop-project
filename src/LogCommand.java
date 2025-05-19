
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Команда за извеждане на логове по зададен период от време.
 */
public class LogCommand implements Command {
    private WarehouseService service;
    private Scanner scanner;

    /**
     * Създава инстанция на LogCommand с достъп до склада и вход от потребителя.
     */
    public LogCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Изпълнява командата за извеждане на логове между две дати.
     * Филтрира записите според начална и крайна дата, въведени от потребителя.
     */
    @Override
    public void execute() {
        System.out.print("Въведи начален период (YYYY-MM-DD): ");
        String fromStr = scanner.nextLine();
        System.out.print("Въведи краен период (YYYY-MM-DD): ");
        String toStr = scanner.nextLine();

        LocalDate from = LocalDate.parse(fromStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate to = LocalDate.parse(toStr, DateTimeFormatter.ISO_LOCAL_DATE);

        List<LogEntry> logs = service.getLogs();

        System.out.println("Лог записи от " + fromStr + " до " + toStr + ":");
        for (LogEntry entry : logs) {
            LocalDate entryDate = LocalDate.parse(entry.getTimestamp().substring(0, 10));
            if ((entryDate.isEqual(from) || entryDate.isAfter(from)) &&
                (entryDate.isEqual(to) || entryDate.isBefore(to))) {
                System.out.println(entry);
            }
        }
    }
}
