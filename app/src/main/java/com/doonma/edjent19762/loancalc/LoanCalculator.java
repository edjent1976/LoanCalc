package com.doonma.edjent19762.loancalc;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.lang.ref.WeakReference;

public class LoanCalculator extends Fragment implements OnClickListener, OnItemSelectedListener {

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
    private static String title = "Loan Calculator";
    private int page;
    OnLoanCalcSelectedListener mCallback;
    private MainActivity myActivity = null;
    private KeyListener listener;
    Spinner spinner;

    public static LoanCalculator newInstance(int page, String title) {
        LoanCalculator loanCalculator = new LoanCalculator();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        loanCalculator.setArguments(args);
        return loanCalculator;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id ){

        loanAmount = null;
        loanAmountText.setText(null);

        interestRate = null;
        interestRateText.setText(null);

        numberOfYears = null;
        numberOfYearsText.setText(null);

        payment = null;
        monthlyPaymentText.setText(null);

        switch(parent.getSelectedItemPosition()) {
            case 0:
                resetColor();
                Toast.makeText(getActivity(), "You Selected Loan Amount", Toast.LENGTH_LONG).show();
                loanAmountText.setText("Value To Be Calculated");
                loanAmountText.setTextColor(Color.RED);
                makeEnabled();
                loanAmountText.setEnabled(false);

                break;

            case 1:
                resetColor();
                Toast.makeText(getActivity(), "You Selected Interest Rate", Toast.LENGTH_LONG).show();
                interestRateText.setText("Value To Be Calculated");
                interestRateText.setTextColor(Color.RED);
                makeEnabled();
                interestRateText.setEnabled(false);
                break;

            case 2:
                resetColor();
                Toast.makeText(getActivity(), "You Selected Number of Years", Toast.LENGTH_LONG).show();
                numberOfYearsText.setText("Value To Be Calculated");
                numberOfYearsText.setTextColor(Color.RED);
                makeEnabled();
                numberOfYearsText.setEnabled(false);

                break;

            case 3:
                resetColor();
                Toast.makeText(getActivity(), "You Selected Monthly Payment", Toast.LENGTH_LONG).show();
                monthlyPaymentText.setText("Value To Be Calculated");
                monthlyPaymentText.setTextColor(Color.RED);
                makeEnabled();
                monthlyPaymentText.setEnabled(false);

                break;

            default:
                resetColor();
                Toast.makeText(getActivity(), "You Have Not Selected Anything", Toast.LENGTH_LONG).show();
                break;
        }


    }


    public void onNothingSelected(AdapterView<?> parent){}

    //Container Activity must implement this interface
    public interface OnLoanCalcSelectedListener{
        public void calculatePayment();
    }

    public void resetColor(){
        monthlyPaymentText.setTextColor(Color.BLACK);
        interestRateText.setTextColor(Color.BLACK);
        loanAmountText.setTextColor(Color.BLACK);
        numberOfYearsText.setTextColor(Color.BLACK);

    }

    public void makeEnabled(){
        loanAmountText.setEnabled(true);
        interestRateText.setEnabled(true);
        numberOfYearsText.setEnabled(true);
        monthlyPaymentText.setEnabled(true);
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

        spinner = (Spinner) getView().findViewById(R.id.value_to_calculate_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getActivity(), R.array.calculate_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


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
        }
    }

    public void calculateMissingValue(){

//        Toast.makeText(getActivity(), payment.toString(), Toast.LENGTH_LONG).show();
        if(spinner.getSelectedItemPosition() == 0){
            calculateLoanAmount();

        }else if(spinner.getSelectedItemPosition() == 1 ) {
            calculateInterestRate();

        }else if(spinner.getSelectedItemPosition() == 2) {
            calculateNumberOfYears();
        }
        else if(spinner.getSelectedItemPosition() ==3) {
            calculatePayment();
        }else{
            Toast.makeText(getActivity(), "Please Select A Value To Calculate", Toast.LENGTH_LONG).show();
        }

    }

    public void calculateInterestRate() {

        interestRateText.setText(null);





        
        Toast.makeText(getActivity(), "The Interest Rate Function Works!!!", Toast.LENGTH_LONG).show();

    }

    public void calculateNumberOfYears(){
        Toast.makeText(getActivity(), "The Calculate Number of Years Function Works!!!", Toast.LENGTH_LONG).show();
        numberOfYearsText.setText(null);

        try {
            if(interestRateText != null && loanAmountText != null && monthlyPaymentText != null){

                double interestRateMonth = interestRate/100/12;
                Toast.makeText(getActivity(),"The Monthly Interest Rate Is = " + interestRateMonth,Toast.LENGTH_LONG ).show();
                double prinDiPaymentMulIrm = (loanAmount/payment) * interestRateMonth;
                Toast.makeText(getActivity(), "Loan Amount Divided Payment Multiplied By IPM: "+ prinDiPaymentMulIrm, Toast.LENGTH_LONG).show();
                double numberMinusOne = 1 - prinDiPaymentMulIrm;
                Toast.makeText(getActivity(), "1 - prinDiPaymentMulIrm: " + numberMinusOne, Toast.LENGTH_LONG ).show();
                double intRateMonthPlus1 = interestRateMonth +1;
                Toast.makeText(getActivity(), "InterestRateMonthPlus1: " + intRateMonthPlus1, Toast.LENGTH_LONG).show();
                double negLog = -Math.log10(.6666667);
                Toast.makeText(getActivity(), "Neg log: " + negLog, Toast.LENGTH_LONG).show();
                double log = Math.log10(intRateMonthPlus1);
                Toast.makeText(getActivity(), "log: " + log, Toast.LENGTH_LONG).show();
                double fiveBySix = negLog/log;
                Toast.makeText(getActivity(), "fiveBySix: " + fiveBySix, Toast.LENGTH_LONG  ).show();
                numberOfYears = (fiveBySix/12.00);
                numberOfYearsText.setText(numberOfYears.toString());



             }else {
                Toast.makeText(getActivity(), "Please Make Sure You Have Entered All Values", Toast.LENGTH_LONG).show();
            }
         }catch(NullPointerException npe) {
                    Toast.makeText(getActivity(), "All Values Have Not Been Entered", Toast.LENGTH_LONG).show();
            }

        }


    public void calculateLoanAmount() {

        loanAmountText.setText(null);
            try {
                if (interestRateText != null && monthlyPaymentText != null && numberOfYearsText != null) {
                    double numerator = 1 - (Math.pow((1 + interestRate/100), -(numberOfYears * 12)));
                    loanAmount = payment * (numerator / (interestRate/100));
                    loanAmountText.setText(loanAmount.toString());
                } else {
                    Toast.makeText(getActivity(), "Please Make Sure You Have Entered All Required Values", Toast.LENGTH_LONG).show();
                }
            }catch(NullPointerException npe){
                Toast.makeText(getActivity(), "Unable To Create A Value", Toast.LENGTH_LONG).show();
            }
    }

    public void calculatePayment() {

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
                    Toast.makeText(getActivity(), "Please Make Sure You Have Entered All Required Values", Toast.LENGTH_LONG).show();
                }//end else statement
            }catch (NullPointerException npe){
                Toast.makeText(getActivity(), "Unable to Create A Value", Toast.LENGTH_LONG).show();
            }
    }

    public void resetPayment(){

       interestRateText.setText(null);
       interestRate = null;

       monthlyPaymentText.setText(null);
       payment = null;

       numberOfYearsText.setText(null);
       numberOfYears = null;

       loanAmountText.setText(null);
       loanAmount = null;

      resetColor();
    }





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






