package com.myrestaurant.app.model;

import com.codename1.io.Log;
import com.codename1.io.Preferences;
import com.codename1.io.Properties;
import com.codename1.l10n.L10NManager;
import com.codename1.properties.MapProperty;
import com.codename1.properties.Property;
import com.codename1.properties.PropertyBusinessObject;
import com.codename1.properties.PropertyIndex;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * This class abstracts basic details about the restaurant, it allow us to keep the UI
 * generic so we can rebuild this app to work for any restaurant out there
 */
public class Restaurant implements PropertyBusinessObject {
    public final Property<String, Restaurant> name = new Property<>("name");
    public final Property<String, Restaurant> tagline = new Property<>("tagline");
    public final Property<Double, Restaurant> latitude = new Property<>("latitude", 0.0);
    public final Property<Double, Restaurant> longitude = new Property<>("longitude", 0.0);
    public final Property<String, Restaurant> navigationAddress = new Property<>("navigationAddress");
    public final Property<String, Restaurant> address = new Property<>("address");
    public final Property<String, Restaurant> phone = new Property<>("phone");
    public final Property<String, Restaurant> website = new Property<>("website");
    public final Property<String, Restaurant> currency = new Property<>("currency", "$");
    public final Property<Menu, Restaurant> menu = new Property<>("menu", new Menu());
    public final Property<Order, Restaurant> cart = new Property<>("order", new Order());
    public final Property<Double, Restaurant> minimumOrder = new Property<>("minimumOrder", 0.0);
    public final Property<Double, Restaurant> shippingRangeKM = new Property<>("shippingRangeKM", 0.0);
    public final Property<Double, Restaurant> deliveryExtraCost = new Property<>("deliveryExtraCost", 0.0);
    
    private final PropertyIndex idx = new PropertyIndex(this, "Restaurant", 
            name, tagline, latitude, longitude, navigationAddress, address, 
            phone, website, currency, menu, cart, minimumOrder, shippingRangeKM,
            deliveryExtraCost);

    private static Restaurant instance;
    
    @Override
    public PropertyIndex getPropertyIndex() {
        return idx;
    }

    private void bindStringPropertyToPreference(Property<String, ? extends Object> p) {
        p.set(Preferences.get(p.getName(), p.get()));
        p.addChangeListener(pl -> Preferences.set(p.getName(), p.get()));
    }

    private void bindDoublePropertyToPreference(Property<Double, ? extends Object> p) {
        p.set(Preferences.get(p.getName(), p.get()));
        p.addChangeListener(pl -> Preferences.set(p.getName(), p.get()));
    }
    
    private Restaurant() {
        bindStringPropertyToPreference(name);
        bindStringPropertyToPreference(tagline);
        bindDoublePropertyToPreference(latitude);
        bindDoublePropertyToPreference(longitude);
        bindStringPropertyToPreference(navigationAddress);
        bindStringPropertyToPreference(address);
        bindStringPropertyToPreference(phone);
        bindStringPropertyToPreference(website);
        bindStringPropertyToPreference(currency);
        bindDoublePropertyToPreference(minimumOrder);
        bindDoublePropertyToPreference(shippingRangeKM);
        bindDoublePropertyToPreference(deliveryExtraCost);
    }

    public static Restaurant getInstance() {
        if(instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }
 
    public String formatCurrency(double value) {
        return currency.get() + L10NManager.getInstance().format(value);
    }
}
