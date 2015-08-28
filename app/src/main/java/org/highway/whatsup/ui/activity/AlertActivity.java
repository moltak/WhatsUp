package org.highway.whatsup.ui.activity;

import android.media.session.MediaController;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.VideoView;

import org.highway.whatsup.R;
import org.highway.whatsup.domain.data.ExpressData;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by engeng on 8/26/15.
 */
public class AlertActivity extends AppCompatActivity {

    @Bind(R.id.videoView) VideoView videoView;
    @Bind(R.id.changeCctv) Button changeCctv;

    private String VIDEOS[] = {
            "http://cctv.ktict.co.kr/175.mp4",
            "http://cctv.ktict.co.kr/99.mp4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);

        ExpressData data = getIntent().getExtras().getParcelable("data");

        Observable.timer(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        finish();
                    }
                });

        videoView.setVideoPath(VIDEOS[0]);
        videoView.start();
    }

    @OnClick(R.id.changeCctv)
    public void changeVideo() {
        videoView.setVideoPath(VIDEOS[1]);
        videoView.start();
    }
}
