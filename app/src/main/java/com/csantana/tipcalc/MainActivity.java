package com.csantana.tipcalc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.csantana.tipcalc.fragments.TipHistoryListFragment;
import com.csantana.tipcalc.fragments.TipHistoryListFragmentListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
        @Bind(R.id.btnSubmit)
        Button btnSubmit;
        @Bind(R.id.inputPercentage)
        EditText inputPercentage;
        @Bind(R.id.btnIncrease)
        Button btnIncrease;
        @Bind(R.id.btnDecrease)
        Button btnDecrease;
        @Bind(R.id.btnClear)
        Button btnClear;
        @Bind(R.id.txtTip)
        TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return super.onCreateOptionsMenu(menu);
            }

                @Override
        public boolean onOptionsItemSelected(MenuItem item) {

                        if (item.getItemId() == R.id.action_about) {
                        about();
                    }

                        return super.onOptionsItemSelected(item);
            }

    @OnClick(R.id.btnSubmit)
        public void handleSubmit() {
                hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();

                        if(!strInputTotal.isEmpty()) {
                        double total = Double.parseDouble(strInputTotal);
                        int tipPercentage = getTipPrecentage();

                                double tip = total * (tipPercentage/100d);
                            String strTip = String.format(getString(R.string.global_message_tip), tip);
                            fragmentListener.action(strTip);
                        txtTip.setVisibility(View.VISIBLE);
                        txtTip.setText(strTip);
                    }
            }
                @OnClick(R.id.btnIncrease)
                public void handleClickIncrease() {
                    hideKeyboard();
                    handleTipChange(TIP_STEP_CHANGE);
                    }
                @OnClick(R.id.btnDecrease)
                public void handleClickDecrease() {
                    hideKeyboard();
                    handleTipChange(-TIP_STEP_CHANGE);
                    }


                public int getTipPrecentage() {
                    int tipPercentage = DEFAULT_TIP_PERCENTAGE;
                    String strInputTipPercentage = inputPercentage.getText().toString().trim();

                            if(!strInputTipPercentage.isEmpty()) {
                                    tipPercentage = Integer.parseInt(strInputTipPercentage);
                                }
                            else {
                                    inputPercentage.setText(String.valueOf(DEFAULT_TIP_PERCENTAGE));
                                }

                            return tipPercentage;
            }

    public void handleTipChange(int change) {
        int tipPercentage = getTipPrecentage();
                tipPercentage += change;

        if(tipPercentage > 0) {
            inputPercentage.setText(String.valueOf(tipPercentage));
        }

                                            }


                private void hideKeyboard() {
                InputMethodManager inputManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                        try {
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (NullPointerException npe) {
                        Log.e(getLocalClassName(), Log.getStackTraceString(npe));
                    }
            }

                private void about() {
                TipCalc app = (TipCalc) getApplication();
                String strUrl = app.getAboutUrl();

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(strUrl));
                startActivity(intent);
            }
}
