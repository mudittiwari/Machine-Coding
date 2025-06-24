package payment;

import bill.Bill;

public class PaymentImpl implements Payment{
    private Bill bill;
    private String receiptNumber;

    public PaymentImpl(Bill bill){
        this.bill = bill;
    }

    private void generateReceipt(){
        this.receiptNumber = "11223344";
    }

    @Override
    public void makePayment(){
        this.generateReceipt();
        System.out.println("Payment of " + bill.getAmount() +" Rs. is successful" + " Receipt Number is: " + receiptNumber);
    }
}
