package livestream.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nttungg.livestream.R;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import livestream.BaseActivity;
import livestream.MyApplication;
import livestream.models.BaseRequest;
import livestream.models.User;
import livestream.utils.StringUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText mEditUsername;
    private EditText mEditPassword;
    private Button mButtonLogin;
    private TextView mTextRegister;
    private Observer<BaseRequest> mObserver ;

    public static Intent getLoginIntent(Context context) {
        Intent mIntent = new Intent(context, LoginActivity.class);
        return mIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();

        mObserver = new Observer<BaseRequest>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseRequest request) {
                Log.d("AMEN", "onNext: " + request.getTypeRequest());
//                switch (request.getTypeRequest()) {
//                    case 0:
//                        showToast(request.getMessage());
//                        break;
//                    default:
//                        break;
//                }
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
        subscribleRequestResponse(mObserver);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                login(mEditUsername.getText().toString(), mEditPassword.getText().toString());
                break;
            case R.id.textView_register:
                startActivity(RegisterActivity.getRegisterIntent(this));
                break;
        }
    }

    private void initUI() {
        mEditUsername = findViewById(R.id.editText_username_login);
        mEditPassword = findViewById(R.id.edittext_password_login);
        mButtonLogin = findViewById(R.id.button_login);
        mTextRegister = findViewById(R.id.textView_register);
        mButtonLogin.setOnClickListener(this);
        mTextRegister.setOnClickListener(this);
    }

    private void login(String username, String password) {
        username = StringUtils.removeAllWhiteSpace(username);
        password = StringUtils.removeAllWhiteSpace(password);

        if (StringUtils.isNullOrBlank(username) || StringUtils.isNullOrBlank(password)) {
            showToast("Wrong format");
        }

        User user = new User(0, username, password, username, "");
        sendRequest(new BaseRequest<>(0, "", user));
    }
}
