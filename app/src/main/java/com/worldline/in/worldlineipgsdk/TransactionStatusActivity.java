package com.worldline.in.worldlineipgsdk;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.awl.merchanttoolkit.dto.ResMsgDTO;
import com.worldline.in.callback.ResultListener;
import com.worldline.in.ipg.TransactionStatus;

public class TransactionStatusActivity extends AppCompatActivity implements ResultListener {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_status);

        final EditText etOrderId = findViewById(R.id.order_id);
        final EditText etTransactionRefNo = findViewById(R.id.ref_no);
        Button btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransactionStatus transactionStatus = new TransactionStatus(TransactionStatusActivity.this,TransactionStatusActivity.this);
                transactionStatus.execute(etOrderId.getText().toString(),etTransactionRefNo.getText().toString());
            }
        });


    }

    @Override
    public void onResult(ResMsgDTO response) {

        Log.d(TAG,""+response);
        Utility.showAlertDialog(this,response.getStatusDesc());
//        Toast.makeText(MainActivity.this,response.getStatusDesc(),Toast.LENGTH_SHORT).show();

    }
}
