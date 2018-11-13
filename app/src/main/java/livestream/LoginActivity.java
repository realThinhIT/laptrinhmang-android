package livestream;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nttungg.livestream.R;

import livestream.server.SocketServerThread;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditUsername;
    private EditText mEditPassword;
    private Button mButtonLogin;
    private TextView mTextRegister;
    SocketServerThread socketServerThread;

    public static Intent getLoginIntent(Context context) {
        Intent mIntent = new Intent(context, LoginActivity.class);
        return mIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        socketServerThread = new SocketServerThread();
        socketServerThread.start();
        initUI();
    }

    private void initUI() {
        mEditUsername = findViewById(R.id.editText_username_login);
        mEditPassword = findViewById(R.id.edittext_password_login);
        mButtonLogin = findViewById(R.id.button_login);
        mTextRegister = findViewById(R.id.textView_register);
        mButtonLogin.setOnClickListener(this);
        mTextRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
//                startActivity(HomeActivity.getHomeIntent(this));
//
//                this.finish();
                break;
            case R.id.textView_register:
                startActivity(RegisterActivity.getRegisterIntent(this));
                break;
        }
    }
}
