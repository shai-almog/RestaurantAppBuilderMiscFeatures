package com.myrestaurant.app.model;

import com.codename1.processing.Result;
import com.codename1.properties.ListProperty;
import com.codename1.properties.MapProperty;
import com.codename1.properties.Property;
import com.codename1.properties.PropertyBusinessObject;
import com.codename1.properties.PropertyIndex;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstraction containing the dishes ordered
 */
public class Order implements PropertyBusinessObject {
    public final Property<String, Order> id = new Property<>("id");
    public final Property<Date, Order> date = new Property<>("date");
    public final Property<Boolean, Order> purchased = new Property<>("purchased");
    public final MapProperty<Dish, Integer, Order> dishQuantity = new MapProperty<>("dishQuantity");
    public final Property<String, Order> notes = new Property<>("notes");
    public final Property<String, Order> nounce = new Property<>("nounce");
    public final Property<String, Order> auth = new Property<>("auth");
    public final Property<Double, Order> latitude = new Property<>("latitude", 0.0);
    public final Property<Double, Order> longitude = new Property<>("longitude", 0.0);
    public final Property<Address, Order> address = new Property<>("address", new Address());

    private final PropertyIndex idx = new PropertyIndex(this, "Order", 
            id, date, purchased, dishQuantity, notes, nounce, auth, latitude, longitude, address);

    @Override
    public PropertyIndex getPropertyIndex() {
        return idx;
    }
    
    public String toJSON() {
        Map m = idx.toMapRepresentation();
        HashMap<String, Integer> dishes = new HashMap();
        for(Map.Entry<Dish, Integer> e : dishQuantity) {
            dishes.put(e.getKey().getPropertyIndex().toJSON(), e.getValue());
        }
        m.remove("address");
        m.remove("dishQuantity");
        String json = Result.fromContent(m).toString();
        json = json.substring(0, json.lastIndexOf('}') - 1);
        json += ",\n\"address\":";
        json += address.get().getPropertyIndex().toJSON();
        json += ",\n\"dishQuantity\": {";
        boolean first = true;
        for(Map.Entry<Dish, Integer> e : dishQuantity) {
            if(!first) {
                json += ",";
            }
            first = false;
            json += "\"" + e.getKey().id.get();
            json += "\":";
            json += e.getValue();
        }
        json += "\n}\n}";
        return json;
        
        // This should work once we fix stuff in properties...
        //return idx.toJSON();        
    }
}
