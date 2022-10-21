package middletermtask.problem02.enums;

public enum ActionCategory {
    SAVE, SEARCH, EXIT;

    /**
     * 어떤 버튼을 눌렀는지 판별하기 위한 메소
     */
    public static ActionCategory findAction(String actionCommand) {
        if (SAVE.name().equals(actionCommand)) {
            return SAVE;
        }
        if (SEARCH.name().equals(actionCommand)) {
            return SEARCH;
        }
        if (EXIT.name().equals(actionCommand)) {
            return EXIT;
        }
        return SAVE;
    }
}
