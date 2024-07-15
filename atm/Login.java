package atm;
/*LOGIN REQUEST*/
public class Login {
    private int accountNum;
    private int pin;

    //constructor
    public Login(int accountNum, int pin){
        this.accountNum = accountNum;
        this.pin = pin;
    }

    /*request login for account number*/
    public void request(){
        System.out.println("WELCOME TO THE ATM PROJECT");
        System.out.println("..........................\n");
        System.out.print("Enter account: ");
        int check = InputHelper.input.nextInt();

        /*check if account number is correct*/

        if(check == accountNum){
            requestPin();  //open the pin request
        }
        else{
            System.out.println("Invalid account!\n");
            //loop to re-ask
            request();
        }
    }

    /*request login for pin*/
    public void requestPin(){
        System.out.print("Enter Pin: ");
        int pinCheck = InputHelper.input.nextInt();

        /*check if pin is correct*/

        if(pinCheck == pin){
            System.out.println("Successfully logged in\n");
            System.out.println("....................\n");
            AccountType.accounts(); //open accounts options
        }else{
            System.out.println("Account or Pin is invalid!");
        }
    }

}