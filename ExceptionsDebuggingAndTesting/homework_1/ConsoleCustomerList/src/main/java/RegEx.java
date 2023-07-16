public record RegEx() {
    public static final String NAME_OR_SURNAME = "[а-яА-Я]+";
    public static final String EMAIL = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+[.][a-zA-Z]+";
    public static final String PHONE = "[+]7[0-9]{10}";
}
