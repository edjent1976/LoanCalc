package com.doonma.edjent19762.loancalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PaymentSchedule extends Fragment {
    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        PaymentSchedule.title = title;
    }

    //Store instance variables
    private static String title = "Payment Schedule";
    private int page;

    //newInstance constructor for creating fragment with arguments
    public static PaymentSchedule newInstance(int page, String title) {
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        paymentSchedule.setArguments(args);
        return paymentSchedule;
     }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_payment_scheudle,container, false);
        //TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        //tvLabel.setText(page + "--" + title);
        return view;
    }

}

