package livestream.screen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nttungg.livestream.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import livestream.BaseActivity;
import livestream.models.BaseRequest;
import livestream.models.User;
import livestream.utils.Constant;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText mEditFullname;
    private EditText mEditUsername;
    private EditText mEditRePassword;
    private EditText mEditPassword;
    private Button mButtonRegister;
    private TextView mTextLogin;
    private Observer<BaseRequest> mObserver;

    public static Intent getRegisterIntent(Context context) {
        Intent mIntent = new Intent(context, RegisterActivity.class);
        return mIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();

        mObserver = new Observer<BaseRequest>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseRequest request) {
                runOnUiThread(() -> {
                    switch (request.getTypeRequest()) {
                        case 1:
                            showToast(request.getMessage());
                            if (request.getMessage().equals("Register success")) {
                               RegisterActivity.this.onBackPressed();
                                RegisterActivity.this.finish();
                            }
                            break;
                        default:
                            break;
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscribeRequestResponse(mObserver);
    }

    @Override
    protected void onStop() {
        mObserver.onComplete();
        super.onStop();
    }

    private void initUI() {
        mEditUsername = findViewById(R.id.editText_username_register);
        mEditPassword = findViewById(R.id.editText_password_register);
        mEditFullname = findViewById(R.id.editText_fullname_register);
        mEditRePassword = findViewById(R.id.editText_repassword_register);
        mButtonRegister = findViewById(R.id.button_register);
        mTextLogin = findViewById(R.id.textView_login);
        mTextLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_register:
                if (!mEditPassword.getText().toString().equals(mEditRePassword.getText().toString())){
                    showToast("Password does not match");
                } else {
                    User user = new User(0, mEditUsername.getText().toString(), mEditPassword.getText().toString(), mEditFullname.getText().toString(), "");
                    sendRequest(new BaseRequest<>(1, "", user));
                }
                break;
            case R.id.textView_login:
                onBackPressed();
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
