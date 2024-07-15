package atm;
/*ACCOUNTS TYPES TO SELECT AND SWITCH TO*/
public class AccountType {

    /*select account*/
    public static void accounts(){
        System.out.println("Select the account type:");
        System.out.println("1. Current Account");
        System.out.println("2. Saves Account");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
        int choice = InputHelper.input.nextInt();
        handleChoice(choice);

    }

    /*switch to a particular account base on the choice selected*/
    private static void handleChoice(int choice) {
        switch(choice){
            case 1:
                CurrentAccount.options();
                break;
            case 2:
                SavesAccount.options();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }
}
