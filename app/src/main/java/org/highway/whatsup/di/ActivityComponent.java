package org.highway.whatsup.di;

import android.app.Activity;

import org.highway.whatsup.di.scope.ForActivity;

import dagger.Component;

/**
 * Created by engeng on 7/29/15.
 */
@ForActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
