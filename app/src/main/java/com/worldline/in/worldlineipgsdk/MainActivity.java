package com.worldline.in.worldlineipgsdk;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.worldline.in.constant.Param;
import com.worldline.in.ipg.PaymentStandard;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private final int paymentRequestCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button standardtransaction =  findViewById(R.id.btn_transaction_standard);
        standardtransaction.setOnClickListener(this);

        Button cardCapture =  findViewById(R.id.btn_transaction_card_capture);
        cardCapture.setOnClickListener(this);

        Button status =  findViewById(R.id.btn_status);
        status.setOnClickListener(this);

        Button cancel =  findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);

        Button refund =  findViewById(R.id.btn_refund);
        refund.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        Intent intent;
        switch(view.getId())
        {
            case R.id.btn_transaction_standard:
                intent = new Intent(this,PaymentStandard.class);
                intent.putExtra(Param.ORDER_ID,""+Utility.getRandomOrderId());
                intent.putExtra(Param.TRANSACTION_AMOUNT,"12000");
                intent.putExtra(Param.TRANSACTION_CURRENCY,"INR");
                intent.putExtra(Param.TRANSACTION_DESCRIPTION,"Sock money");
                intent.putExtra(Param.TRANSACTION_TYPE,"S");
                startActivityForResult(intent,paymentRequestCode);
                break;

            case R.id.btn_transaction_card_capture:
                intent = new Intent(this,CardCapture.class);
                startActivity(intent);
                break;

            case R.id.btn_status:
                intent = new Intent(this,TransactionStatusActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_cancel:
                intent = new Intent(this,CancelActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_refund:
                intent = new Intent(this,RefundActivity.class);
                startActivity(intent);
                break;
        }

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
                String addField10 = data.getStringExtra(Param.ADDL_FIELD_10);

                String msg = "Status desc: " + statusDescription + "\nRef No: " + transactionRefNo+ "\nOrder id: "+orderId;
                Utility.showAlertDialog(this,msg);

            }
        }
    }
}
