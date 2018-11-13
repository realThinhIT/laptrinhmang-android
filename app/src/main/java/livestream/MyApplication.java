package livestream;

import android.app.Application;

import java.io.IOException;

import io.reactivex.Observer;
import livestream.models.BaseRequest;
import livestream.server.SocketServerThread;

public class MyApplication extends Application {

    private SocketServerThread mSocketServerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        mSocketServerThread = new SocketServerThread();
        mSocketServerThread.start();
    }

    public void sendRequest(BaseRequest request) throws IOException, ClassNotFoundException {
        mSocketServerThread.sendRequest(request);
    }

    public void subscribeRequestResponse(Observer<BaseRequest> observer) {
        mSocketServerThread.subscribeRequestResponse(observer);
    }
}
