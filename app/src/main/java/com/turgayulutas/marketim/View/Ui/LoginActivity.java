package com.turgayulutas.marketim.View.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Switch;

import com.turgayulutas.marketim.View.Custom.CustomEditText;
import com.turgayulutas.marketim.View.Custom.CustomTextView;
import com.turgayulutas.marketim.R;
import com.turgayulutas.marketim.Utils.Preferences;

/**
 * Created by turgayulutas on 22/09/2019.
 */

public class LoginActivity extends Activity {
    CustomEditText mEd_username, mEd_password;
    CustomTextView mTv_result;
    Switch mSw_RememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        *  Activity içinde kullanılacak view/obje 'lerin tanımlanması
        */
        createClassObjects();
    }

    public void signIn(View view) {
        mTv_result.setText("");
        String mUsername = mEd_username.getText().toString().trim();
        String mPassword = mEd_password.getText().toString().trim();


        /*
         *  Boş ve yanlış girişlerin kontrolü ve hatanın ekranda yazdırılması
         */

        if (TextUtils.isEmpty(mUsername)) {
            mTv_result.setText(getResources().getString(R.string.empty_username));
            return;
        }

        if (TextUtils.isEmpty(mPassword)) {
            mTv_result.setText(getResources().getString(R.string.empty_password));
            return;
        }

        if (!mUsername.equals(getResources().getString(R.string.default_username)) || !mPassword.equals(getResources().getString(R.string.default_password))) {
            mTv_result.setText(getResources().getString(R.string.wrong_username_or_password));
            return;
        }

        /*
         * Beni hatırla fonksiyonunun çağırılması
         * */
        if (mSw_RememberMe.isChecked()) {
            rememberMe(mUsername, mPassword);
        }

        /*
         *  myOrders Activity'nin çağırılması
         */
        openMyOrders();

    }

    private void createClassObjects() {
        mEd_password = findViewById(R.id.ed_loginPassword);
        mEd_username = findViewById(R.id.ed_loginUsername);
        mTv_result = findViewById(R.id.tv_loginResult);
        mSw_RememberMe = findViewById(R.id.sw_rememberMe);
    }

    private void rememberMe(String _username, String _password) {
        Preferences.save(this, "userUsername", _username);
        Preferences.save(this, "userPassword", _password);
    }

    private void openMyOrders() {
        Intent myOrdersIntent = new Intent(this, MyOrdersActivity.class);
        myOrdersIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myOrdersIntent);
    }

    @Override
    protected void onResume() {
        /*
         *  Preferences kayıtlarından -userUsername- datasının kayıtlı olup olmadığının kontrolü
         *  */
        if (Preferences.getPrefs(this).contains("userUsername")) {
            startActivity(new Intent(this, MyOrdersActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        super.onResume();
    }
}
