package com.mercadopago;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import com.mercadopago.views.MPTextView;

public class TermsAndConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView();

        // Set terms and conditions
        if (getIntent().getStringExtra("termsAndConditions") != null) {
            MPTextView termsAndConditions = (MPTextView) findViewById(R.id.termsAndConditions);
            termsAndConditions.setText(getIntent().getStringExtra("termsAndConditions"));
        }
        else
        {
            String siteId = getIntent().getStringExtra("siteId");
            if(siteId != null) {
                WebView webview = new WebView(this);
                setContentView(webview);
                if(siteId.equals("MLA")) {
                    webview.loadUrl("https://www.mercadopago.com.ar/ayuda/terminos-y-condiciones_299");
                }
                else if (siteId.equals("MLM")){
                    webview.loadUrl("https://www.mercadopago.com.mx/ayuda/terminos-y-condiciones_715");
                }
                else {
                    finish();
                }
            } else {
                finish();
            }
        }
    }

    protected void setContentView() {
        setContentView(R.layout.activity_terms_and_conditions);
    }
}