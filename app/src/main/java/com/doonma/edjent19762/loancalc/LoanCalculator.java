package com.doonma.edjent19762.loancalc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.lang.ref.WeakReference;

public class LoanCalculator extends Fragment implements OnClickListener {

    EditText loanAmountText;
    EditText interestRateText;
    EditText numberOfYearsText;
    EditText monthlyPaymentText;
    Button resetButton;
    Button calculateButton;
    Double loanAmount = null;
    Double interestRate =null;
    Double numberOfYears= null;
    Double payment =null;
    int counter = 0;
    private static String title = "Loan Calculator";
    private int page;
    OnLoanCalcSelectedListener mCallback;
    private MainActivity myActivity = null;

    public static LoanCalculator newInstance(int page, String title) {
        LoanCalculator loanCalculator = new LoanCalculator();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        loanCalculator.setArguments(args);
        return loanCalculator;
    }

    //Container Activity must implement this interface
    public interface OnLoanCalcSelectedListener{
        public void calculatePayment();
    }


    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        LoanCalculator.title = title;
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle icicle){
        super.onInflate(activity, attrs, icicle);
    }
    @Override
    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.myActivity =(MainActivity)myActivity;
    }
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    //Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_calculator, container, false);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle icicle) {
        super.onActivityCreated(icicle);
        //TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        //tvLabel.setText(page + "--" + title);
        loanAmountText = ((EditText) getView().findViewById(R.id.loan_amount_text));
        loanAmountText.addTextChangedListener(new TextValidator((EditText) getView().findViewById(R.id.loan_amount_text)));

        interestRateText = ((EditText) getView().findViewById(R.id.interest_rate_text));
        interestRateText.addTextChangedListener(new TextValidator((EditText) getView().findViewById(R.id.interest_rate_text)));

        numberOfYearsText = ((EditText) getView().findViewById(R.id.number_of_years_text));
        numberOfYearsText.addTextChangedListener(new TextValidator((EditText) getView().findViewById(R.id.number_of_years_text)));

        monthlyPaymentText = ((EditText) getView().findViewById(R.id.monthly_payment_text));
        monthlyPaymentText.addTextChangedListener(new TextValidator((EditText) getView().findViewById(R.id.monthly_payment_text)));

        Button calculateButton = (Button) getView().findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(this);
        Button resetButton = (Button) getView().findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
    }


    private class TextValidator implements TextWatcher{
        private WeakReference<EditText> mEditText;

        public TextValidator(EditText et) {
            mEditText = new WeakReference<EditText>(et);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s)
        {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            final EditText et = mEditText.get();
            if(et !=null) {
                switch(et.getId()) {
                    case R.id.loan_amount_text:
                        loanAmountText = (EditText) getView().findViewById(R.id.loan_amount_text);
                        try{
                            loanAmount = Double.parseDouble(loanAmountText.getText().toString());
                            Toast.makeText(getActivity(), "The value of loan amount is: " + loanAmount, Toast.LENGTH_LONG).show();
                        }catch(NumberFormatException nfe) {
                            et.setError("Please enter a valid number");
                        }//end try catch statement
                        break;

                    case R.id.interest_rate_text:
                        interestRateText = (EditText) getView().findViewById(R.id.interest_rate_text);
                        try{
                            interestRate = Double.parseDouble(interestRateText.getText().toString());
                            Toast.makeText(getActivity(), "The value of interest rate is: " + interestRate, Toast.LENGTH_LONG).show();
                        }catch(NumberFormatException nfe) {
                            et.setError("Please enter a valid number");
                        }//end try catch statement
                        break;

                    case R.id.number_of_years_text:
                        numberOfYearsText = (EditText) getView().findViewById(R.id.number_of_years_text);

                        try{
                            numberOfYears = Double.parseDouble(numberOfYearsText.getText().toString());
                            Toast.makeText(getActivity(), "The value for number of years is: ) " + numberOfYears, Toast.LENGTH_LONG).show();
                        }catch(NumberFormatException nfe) {
                            et.setError("Please enter a valid number");
                        }
                        break;

                    case R.id.monthly_payment_text:
                        monthlyPaymentText = (EditText) getView().findViewById(R.id.monthly_payment_text);
                        try{
                            payment = Double.parseDouble(monthlyPaymentText.getText().toString());
                        }catch(NumberFormatException nfe) {
                            et.setError("Please enter a valid number");
                        }
                        break;
                }

            }
        }OnLoanCalcSelectedListener mCallback;
    }

    public void calculateMissingValue() {

        //Toast.makeText(getActivity(),  "Value of counter is = " + counter, Toast.LENGTH_LONG ).show();
        //if (isAbleToCalculate() == true) {
          /*  Toast.makeText(getActivity(), "the loan amount is: " + loanAmount, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "the interest rate is: " + interestRate, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "the number of years are: " +numberOfYears, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "the monthly payment is: " + payment, Toast.LENGTH_LONG).show();
           */

            FINISH FLESHING THIS OUT
            if (payment == null && interestRate != null && numberOfYears != null && loanAmount !=null) {
                calculatePayment();
                payment =null;
            } else if (interestRate == null && payment !=null  && numberOfYears !=null && loanAmount !=null) {
                //interest rate function to be name later
                calculateInterestRate();
                interestRate=null;
            } else if (numberOfYears == null) {
                //number of years function to be named later
                //calculateNumberOfYears();
            } else {
                //function to calculate the loan amount
                //calculateLoanAmount();
                Toast.makeText(getActivity(), "Three or more of the values need to be filled", Toast.LENGTH_LONG).show();
            }





      }


    public void calculateInterestRate() {
        Toast.makeText(getActivity(), "The Interest Rate Function Works!!!", Toast.LENGTH_LONG).show();
    }

    public void calculatePayment() {


        double ratePerPeriod = interestRate / 100 / 12;
        Toast.makeText(getActivity(), "The Rate Per Period is: " + ratePerPeriod, Toast.LENGTH_LONG).show();
        double numerator = ratePerPeriod * loanAmount;

        double denominator = 1 - (Math.pow(1 + ratePerPeriod, (-numberOfYears * 12)));
        payment = numerator / denominator;
        monthlyPaymentText.setText(Double.toString(payment));



    }

    public void resetPayment(){
       interestRate = null;
       interestRateText.setText(Double.toString(0.0));
       payment = null;
       monthlyPaymentText.setText(Double.toString(0.0));
       numberOfYears = null;
       numberOfYearsText.setText(Double.toString(0.0));
       loanAmount = null;
       loanAmountText.setText(Double.toString(0.0));
       counter = 0;
    }


    /*public boolean isAbleToCalculate() {

        if(interestRate != null) {
            counter++;
        }
        if(loanAmount !=null) {
            counter++;
        }
        if(payment != null) {
            counter++;
        }
        if(numberOfYears !=null) {
            counter ++;
        }
        if(counter == 3){
            return true;
        }else{
            return false;
        }

    }*/

    @Override
    //Switch statement is not working here.
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.calculate_button:
               calculateMissingValue();

                break;

            case R.id.reset_button:
                resetPayment();
                //interestRate = null;
                //interestRateText.setText.
                //payment = null;
                //monthlyPaymentText.setText("");
                //loanAmount=null;
                //loanAmountText.setText("");
                //numberOfYears = null;
                //numberOfYearsText.setText("");
                break;

            }
        }

    }






