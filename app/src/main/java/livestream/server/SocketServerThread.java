package livestream.server;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import livestream.models.BaseRequest;

public class SocketServerThread extends Thread {

    public PublishSubject<BaseRequest> mRequestResponseEmitter = PublishSubject.create();

    private ObjectOutputStream mObjectOutputStream;
    private ObjectInputStream mObjectInputStream;
    private Socket mSocket;

    @Override
    public void run() {
        try {
            initSocket();
        } catch (Exception e) {
            Log.d("SocketServerThread", "run: " + e.getMessage());
        }
    }

    private void initSocket() throws IOException, ClassNotFoundException {
        mSocket = new Socket("45.77.47.122", 9999);

        mObjectOutputStream = new ObjectOutputStream(mSocket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(mSocket.getInputStream());

        while (true) {
            BaseRequest baseRequest = (BaseRequest) objectInputStream.readObject();
            if (baseRequest != null) {
                Log.d("AMEN-SocketServerThread", "baseRequest: " +
                        baseRequest.getTypeRequest() + " " + baseRequest.getMessage());
                mRequestResponseEmitter.onNext(baseRequest);
            }
        }
    }

    public void sendRequest(BaseRequest request) throws IOException, ClassNotFoundException {
        if (mSocket == null || mSocket.isClosed() || mObjectOutputStream == null) {
            initSocket();
        }

        mObjectOutputStream.writeObject(request);
    }

    public void subscribeRequestResponse(Observer<BaseRequest> observer) {
        mRequestResponseEmitter.subscribe(observer);
    }
}