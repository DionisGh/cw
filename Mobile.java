public class Mobile extends Gadget {
    private int callingCredit;
    public Mobile(String model, double price, int weight, String size, int callingCredit) {
        super(model, price, weight, size);
        this.callingCredit = callingCredit;
    }
    
    public int getCallingCredit() {
        return callingCredit;
    }
    
    public void addCallingCredit(int additionalMinutes) {
        if (additionalMinutes > 0) {
            callingCredit += additionalMinutes;
            System.out.println("Added " + additionalMinutes + " minutes to your calling credit.");
        } else {
            System.out.println("Please enter a positive amount of minutes.");
        }
    }
    
    public boolean makeCall(String phoneNumber, int duration) {
        if (callingCredit >= duration) {
            callingCredit -= duration;
            return true;
        } else {
            return false;
        }
    }
    
    
}
