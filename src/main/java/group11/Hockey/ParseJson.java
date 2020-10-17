package group11.Hockey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParseJson {
	
	public  <T> List<T> parseElement(Class<?> aClass, JSONObject jsonObj, IAttribute attribute) throws Exception {
		T object;
		List<T> objectList = new ArrayList<T>();
		JSONArray jsonArray = attribute.getJsonArray(jsonObj);
		if(jsonArray == null) {
			return null;
		}
		Iterator<JSONObject> listIterator = jsonArray.iterator();
		while (listIterator.hasNext()) {
			object = (T) aClass.newInstance();
			JSONObject listJsonObject = listIterator.next();
			
			attribute.setAttributes(object,listJsonObject);
			objectList.add(object);
		}
		return objectList;
	}

}
