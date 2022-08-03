package com.majimob.app.restaurantbuilder.ui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.majimob.app.restaurantbuilder.model.AppSettings;
import com.majimob.app.restaurantbuilder.model.Builder;

public class AppForm extends BaseNavigationForm {
    private Builder b = new Builder();
    
    public AppForm(AppSettings app) {
        super(app, new BorderLayout());
        
        Container content = new Container(BoxLayout.y());
        addTextAndLabel(content, "Package Name", "");
        addTextAndLabel(content, "App Name", "");
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        
        Button help = new Button("Learn More", "GreenButton");
        Button build = new Button("Build App", "GreenButton");
        FontImage.setMaterialIcon(help, FontImage.MATERIAL_HELP);
        FontImage.setMaterialIcon(build, FontImage.MATERIAL_PHONE_IPHONE);
        
        add(BorderLayout.SOUTH, GridLayout.encloseIn(2, help, build));
        build.addActionListener(e -> b.buildApp(app));
    }
    
    private TextField addTextAndLabel(Container content, String label, String value) {
        TextField tf = new TextField(value);
        tf.setHint(label);
        content.add(new Label(label, "TextFieldLabel")).
                add(tf);
        return tf;
    }

    @Override
    protected boolean isAppForm() {
        return true;
    }
}
