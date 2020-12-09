package com.worldline.in.worldlineipgsdk;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.worldline.in.constant.Param;
import com.worldline.in.ipg.PaymentCardCapture;


public class CardCapture extends AppCompatActivity {

    private final int paymentRequestCode = 1;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_capture);

        final EditText customerName =  findViewById(R.id.til_customer_name);
        final EditText cardNumber =  findViewById(R.id.til_card_number);
        final EditText expiryMonth =  findViewById(R.id.til_expiry_month);
        final EditText expiryYear =  findViewById(R.id.til_expiry_year);
        final EditText cvv =  findViewById(R.id.til_cvv);




        Button pay =  findViewById(R.id.btn_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardCapture.this, PaymentCardCapture.class);
                intent.putExtra(Param.ORDER_ID, "" + Utility.getRandomOrderId());
                intent.putExtra(Param.TRANSACTION_AMOUNT, "12000");
                intent.putExtra(Param.TRANSACTION_CURRENCY, "INR");
                intent.putExtra(Param.TRANSACTION_DESCRIPTION, "Sock money");
                intent.putExtra(Param.TRANSACTION_TYPE, "S");
                intent.putExtra(Param.NAME_ON_CARD, customerName.getText().toString());
                intent.putExtra(Param.CARD_NUMBER, cardNumber.getText().toString());
                intent.putExtra(Param.EXPIRY_DATE, expiryMonth.getText().toString() + expiryYear.getText().toString());
                intent.putExtra(Param.CVV, cvv.getText().toString());
                intent.putExtra(Param.PAYMENT_TYPE, "DC");
                startActivityForResult(intent, paymentRequestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == paymentRequestCode) {
            if (resultCode == RESULT_OK) {
                String orderId = data.getStringExtra(Param.ORDER_ID);
                String transactionRefNo = data.getStringExtra(Param.TRANSACTION_REFERENCE_NUMBER);
                String rrn = data.getStringExtra(Param.RRN);
                String statusCode = data.getStringExtra(Param.STATUS_CODE);
                String statusDescription = data.getStringExtra(Param.STATUS_DESCRIPTION);
                String transactionAmount = data.getStringExtra(Param.TRANSACTION_AMOUNT);
                String requestDate = data.getStringExtra(Param.TRANSACTION_REQUEST_DATE);
                String authNStatus = data.getStringExtra(Param.AUTH_N_STATUS);
                String authZstatus = data.getStringExtra(Param.AUTH_Z_STATUS);
                String captureStatus = data.getStringExtra(Param.CAPTURE_STATUS);
                String pgRefCancelReqId = data.getStringExtra(Param.PG_REF_CANCEL_REQ_ID);
                String refundAmount = data.getStringExtra(Param.REFUND_AMOUNT);
                String addField1 = data.getStringExtra(Param.ADDL_FIELD_1);
                String addField2 = data.getStringExtra(Param.ADDL_FIELD_2);
                String addField3 = data.getStringExtra(Param.ADDL_FIELD_3);
                String addField4 = data.getStringExtra(Param.ADDL_FIELD_4);
                String addField5 = data.getStringExtra(Param.ADDL_FIELD_5);
                String addField6 = data.getStringExtra(Param.ADDL_FIELD_6);
                String addField7 = data.getStringExtra(Param.ADDL_FIELD_7);
                String addField8 = data.getStringExtra(Param.ADDL_FIELD_8);
                String addField9 = data.getStringExtra(Param.ADDL_FIELD_9);

                String msg = "Status desc: " + statusDescription + "\nRef No: " + transactionRefNo+ "\nOrder id: "+orderId;
                Utility.showAlertDialog(this,msg);

            }
        }
    }
}
