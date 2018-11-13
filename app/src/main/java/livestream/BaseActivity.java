package livestream;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import io.reactivex.Observer;
import livestream.models.BaseRequest;

public abstract class BaseActivity extends AppCompatActivity {

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id) {
        Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void notifyErrorConnectionToServer() {
        showToast("Lỗi kết nối! Vui lòng thử lại!");
    }

    public void sendRequest(BaseRequest request) {
        new Thread() {
            @Override
            public void run() {
                try {
                    ((MyApplication) getApplication()).sendRequest(request);
                } catch (IOException | ClassNotFoundException e) {
                    runOnUiThread(() -> {
                        Log.d("AMEN - BaseActivity", "sendRequest: " + e);
                        notifyErrorConnectionToServer();
                    });
                }
            }
        }.start();
    }

    public void subscribeRequestResponse(Observer<BaseRequest> observer) {
        ((MyApplication) getApplication()).subscribeRequestResponse(observer);
    }
}
