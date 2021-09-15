import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class RecursiveJSON {
    private Object root;

    public RecursiveJSON() {
        this.root = null;
    }

    public RecursiveJSON(Object root) {
        this.root = root;
    }

    public RecursiveJSON getObject(String key) {
        if (root == null) {
            return new RecursiveJSON();
        } else {
            return new RecursiveJSON(((JSONObject) root).get(key));
        }
    }

    public ArrayList<RecursiveJSON> getArray(String key) {
        ArrayList<RecursiveJSON> arrayList = new ArrayList<>();
        if (root != null) {
            JSONArray arr = (JSONArray) ((JSONObject) root).get(key);
            for (Object obj : arr) {
                arrayList.add(new RecursiveJSON(obj));
            }
        }
        return arrayList;
    }

    public Object getValue(String key) {
        if (root == null) {
            return null;
        } else {
            return ((JSONObject) root).get(key);
        }
    }
}
