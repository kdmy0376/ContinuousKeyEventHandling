package koo.dw.aiden.android.Continuous_Keystroke_Processing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


/**************************************************************************************************
 *
 * Date : 2021-03-12
 * Author : Aiden_DW_Koo
 * Description : An example of handling keys that are continuously input using a handler.
 *
 **************************************************************************************************/

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final long DELAY_TIME = 300;
    private static final int MSG_KEY_PROCESS = 1;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler(mHandlerCallback);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_0) {
                /* Ignore keyEvent (KEYCODE_0) if it comes in many times within 300 milliseconds */
                mHandler.removeMessages(MSG_KEY_PROCESS);
                mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_KEY_PROCESS), DELAY_TIME);
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private Handler.Callback mHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.what == MSG_KEY_PROCESS) {
                processKeyCode0();
            }
            return false;
        }
    };

    /* Process KeyCode 0 */
    private void processKeyCode0() {
        Log.d(TAG, "processKeyCode0: keyCode 0!");
    }
}