package com.doonma.edjent19762.loancalc;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by edjent1976 on 1/19/15.
 */
public class Formula {


    private EditText loanAmountText = null;
    private EditText interestRateText = null;
    private EditText monthlyPaymentText = null;
    private EditText numberOfYearsText = null;

    Context context;

    private Double loanAmount;
    private Double interestRate;
    private Double payment;
    private Double numberOfYears;


    Formula(){}

    Formula(Context context){
        this.context = context;

    }

    Formula (EditText loanAmountText, EditText interestRateText, EditText monthlyPaymentText, EditText numberOfYearsText){
        this.loanAmountText = loanAmountText;
        this.interestRateText = interestRateText;
        this.monthlyPaymentText = monthlyPaymentText;
        this.numberOfYearsText = numberOfYearsText;
    }

    public EditText getLoanAmountText() {
        return loanAmountText;
    }

    public void setLoanAmountText(EditText loanAmountText) {
        this.loanAmountText = loanAmountText;
    }

    public EditText getInterestRateText() {
        return interestRateText;
    }

    public void setInterestRateText(EditText interestRateText) {
        this.interestRateText = interestRateText;
    }

    public EditText getMonthlyPaymentText() {
        return monthlyPaymentText;
    }

    public void setMonthlyPaymentText(EditText monthlyPaymentText) {
        this.monthlyPaymentText = monthlyPaymentText;
    }

    public EditText getNumberOfYearsText() {
        return numberOfYearsText;
    }

    public void setNumberOfYearsText(EditText numberOfYearsText) {
        this.numberOfYearsText = numberOfYearsText;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getMonthlyPayment() {
        return payment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.payment = payment ;
    }

    public Double getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Double numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void calculatePayment(){
        monthlyPaymentText.setText(null);
        try {
            if (loanAmountText != null && interestRateText != null && numberOfYearsText != null) {
                double ratePerPeriod = interestRate / 100 / 12;
                //Toast.makeText(getActivity(), "The Rate Per Period is: " + ratePerPeriod, Toast.LENGTH_LONG).show();
                double numerator = ratePerPeriod * loanAmount;
                double denominator = 1 - (Math.pow(1 + ratePerPeriod, (-numberOfYears * 12)));
                payment = numerator / denominator;
                monthlyPaymentText.setText(payment.toString());
            } else {
                Toast.makeText(context, "Please Make Sure You Have Entered All Required Values", Toast.LENGTH_LONG).show();
            }//end else statement
        }catch (NullPointerException npe){
            Toast.makeText (context,"Unable to Create A Value", Toast.LENGTH_LONG).show();
        }
       }
    }


