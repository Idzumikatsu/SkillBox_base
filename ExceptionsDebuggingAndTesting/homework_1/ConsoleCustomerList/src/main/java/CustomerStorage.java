import MyExeptions.WrongEmailFormat;
import MyExeptions.WrongNameOrSurnameFormat;
import MyExeptions.WrongPhoneFormat;
import MyExeptions.WrongWordsCount;

import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    private final int INDEX_NAME = 0;
    private final int INDEX_SURNAME = 1;
    private final int INDEX_EMAIL = 2;
    private final int INDEX_PHONE = 3;

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
            checkInputString(components);
            String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    private void checkInputString(String[] components)
    throws WrongEmailFormat, WrongPhoneFormat, WrongNameOrSurnameFormat, WrongWordsCount {
        int componentsCount = 4;
        if (components.length != componentsCount) {
            throw new WrongWordsCount(ErrorMessages.WORDS_COUNT);
        } else if (!components[INDEX_NAME].matches(RegEx.NAME_OR_SURNAME) ||
                !components[INDEX_SURNAME].matches(RegEx.NAME_OR_SURNAME)) {
            throw new WrongNameOrSurnameFormat(ErrorMessages.NAME_OR_SURNAME);
        } else if (!components[INDEX_EMAIL].matches(RegEx.EMAIL)) {
            throw new WrongEmailFormat(ErrorMessages.EMAIL);
        } else if (!components[INDEX_PHONE].matches(RegEx.PHONE)) {
            throw new WrongPhoneFormat(ErrorMessages.PHONE);
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}