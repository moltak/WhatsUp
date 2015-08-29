package org.highway.whatsup.ui.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
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
    @Bind(R.id.buttonContact) Button changeCctv;
    @Bind(R.id.textViewAlertDesc) TextView textViewAlertDesc;
    @Bind(R.id.textViewSpeed) TextView textViewSpeed;
    @Bind(R.id.textViewDistance) TextView textViewDistance;
    @Bind(R.id.textViewLaneBlockState) TextView textViewLaneBlockState;
    @Bind(R.id.textViewDirection) TextView textViewDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);

        ExpressData data = getIntent().getExtras().getParcelable("data");
        textViewAlertDesc.setText(data.getMsg());
        textViewSpeed.setText(String.format("%3.0fKm/h", data.getSpeed() * 3.6));
        textViewLaneBlockState.setText(data.getLaneBlock());
        textViewDistance.setText(String.format("%3.1fKm", calculateDistance(data)));
        textViewDirection.setText(data.getDirection());

        if (!TextUtils.isEmpty(data.getCctvUrl())) {
            videoView.setVideoPath(data.getCctvUrl());
            videoView.start();
        }

        Observable.timer(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
//                        finish();
                    }
                });
    }

    @OnClick(R.id.buttonContact)
    public void contactButtonClicked() {
    }

    private double calculateDistance(ExpressData expressData) {
        float result[] = new float[5];
        Location.distanceBetween(
                expressData.getLat(), expressData.getLng(),
                expressData.getOccuredLat(), expressData.getOccuredLng(),
                result);
        return result[0];
    }
}
