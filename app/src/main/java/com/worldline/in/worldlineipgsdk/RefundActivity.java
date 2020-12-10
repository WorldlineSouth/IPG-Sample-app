package com.worldline.in.worldlineipgsdk;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.awl.merchanttoolkit.dto.ResMsgDTO;
import com.worldline.in.callback.ResultListener;
import com.worldline.in.ipg.RefundTransaction;

public class RefundActivity extends AppCompatActivity implements ResultListener {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_status);

        final EditText etOrderId =  findViewById(R.id.order_id);
        final EditText etTransactionRefNo =  findViewById(R.id.ref_no);
        Button btnOk =  findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etOrderId.getText().toString().equals("") && !etTransactionRefNo.getText().toString().equals("")) {
                RefundTransaction refundTransaction = new RefundTransaction(RefundActivity.this, RefundActivity.this);
                refundTransaction.execute(etOrderId.getText().toString(), etTransactionRefNo.getText().toString());
            }else{
                Toast.makeText(RefundActivity.this,"All fields are Mandatory", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    @Override
    public void onResult(ResMsgDTO response) {

        Log.d(TAG, "" + response);
        Utility.showAlertDialog(this, response.getStatusDesc());
//        Toast.makeText(MainActivity.this,response.getStatusDesc(),Toast.LENGTH_SHORT).show();

    }

}
