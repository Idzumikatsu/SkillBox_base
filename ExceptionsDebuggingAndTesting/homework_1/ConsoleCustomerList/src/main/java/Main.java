import MyExeptions.WrongEmailFormat;
import MyExeptions.WrongNameOrSurnameFormat;
import MyExeptions.WrongPhoneFormat;
import MyExeptions.WrongWordsCount;
import org.apache.logging.log4j.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;
    private static final Logger FILE_LOGGER = LogManager.getLogger(Main.class);
    private static final Marker QUERIES_MARKER = MarkerManager.getMarker("QUERIES");
    private static final Marker ERRORS_MARKER = MarkerManager.getMarker("ERRORS");


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();


        while (true) {
            String command = scanner.nextLine();
            FILE_LOGGER.info(QUERIES_MARKER, "Пользователь ввел строку : {}", command);
            String[] tokens = command.split("\\s+", 2);

            switch (tokens[0]) {
                case "add" -> {
                    try {
                        executor.addCustomer(tokens[1]);
                    } catch (WrongEmailFormat | WrongNameOrSurnameFormat | WrongPhoneFormat |
                             WrongWordsCount | ArrayIndexOutOfBoundsException e) {
                        FILE_LOGGER.error(ERRORS_MARKER, e.getClass(), e);
                        e.printStackTrace();
                    }
                }
                case "list" -> executor.listCustomers();
                case "remove" -> executor.removeCustomer(tokens[1]);
                case "count" -> System.out.println("There are " + executor.getCount() + " customers");
                case "help" -> System.out.println(helpText);
                default -> System.out.println(COMMAND_ERROR);
            }
        }
    }
}
