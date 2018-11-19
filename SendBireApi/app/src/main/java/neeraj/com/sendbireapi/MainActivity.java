package neeraj.com.sendbireapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sendbird.android.OpenChannel;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendBird.init(getString(R.string.APP_ID),this);
        SendBird.connect(getString(R.string.USER_ID), new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e)
            {
                if(e!=null)
                {
                    return;
                }
            }
        });
        OpenChannel.createChannel(new OpenChannel.OpenChannelCreateHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {
                if(e!=null)
                {
                    return;
                }
            }
        });
        OpenChannel.getChannel(getString(R.string.CHANNEL_URL), new OpenChannel.OpenChannelGetHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {
                if(e!=null)
                {
                    return;
                }
                openChannel.enter(new OpenChannel.OpenChannelEnterHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        if(e!=null)
                        {
                            return;
                        }
                    }
                });
                openChannel.
            }
        });

    }
}
