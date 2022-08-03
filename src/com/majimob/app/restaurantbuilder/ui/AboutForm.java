package com.majimob.app.restaurantbuilder.ui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.majimob.app.restaurantbuilder.model.AppSettings;

public class AboutForm extends BaseNavigationForm {
    
    public AboutForm(AppSettings app) {
        super(app, BoxLayout.y());
        add(new Label("Coming Soon"));
    }

    @Override
    protected boolean isAboutForm() {
        return true;
    }
}
