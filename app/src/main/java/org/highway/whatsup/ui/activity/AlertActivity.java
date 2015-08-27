package org.highway.whatsup.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.highway.whatsup.R;
import org.highway.whatsup.domain.data.ExpressData;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by engeng on 8/26/15.
 */
public class AlertActivity extends AppCompatActivity {

    @Bind(R.id.textViewMsg) TextView textViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);

        ExpressData data = getIntent().getExtras().getParcelable("data");
        textViewMsg.setText(data.getMsg());

        Observable.timer(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        finish();
                    }
                });
    }
}
