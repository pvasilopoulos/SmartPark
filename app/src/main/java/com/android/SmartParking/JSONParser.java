package com.android.SmartParking;

import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;
 
public class JSONParser {
 
      public JSONParser()
      {
      super();
      }
 
     
      public boolean parseUserAuth(JSONObject object)
      {     boolean userAtuh=false;
                  try {
                        userAtuh= object.getBoolean("Value");
                  } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        Log.d("JSONParser => parseUserAuth", e.getMessage());
                  }
 
                  return userAtuh;
      }
 
      public String parseUId(JSONObject object)
      {
            String c1 ="";
            try {
            		String Id= object.getString("Value");
            		c1 = Id;
            		Log.d("JSONParser => parseUserDetails", "CId : "+c1);
            } catch (JSONException e) {
                  c1=e.getMessage();
            }
            return c1;
      }
      
      public Boolean parseAddUser(JSONObject object)
      {
    	  Boolean name = false;
            try {
            	
            	name = object.getBoolean("Value");
            	Log.d("JSONParser => parseUserDetails", "Name :"+name);
            } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  Log.d("JSONParser => parseUserDetails", e.getMessage());
            }
            return name;
      }
      
      public String parseName(JSONObject object)
      {
            String name = "";
            try {
            	
            	name = object.getString("Value");
            	Log.d("JSONParser => parseUserDetails", "Name :"+name);
            } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  Log.d("JSONParser => parseUserDetails", e.getMessage());
            }
            return name;
      }
      
      
      public String parseUpdateBal(JSONObject object)
      {
    	  String Comp = "false";
            try {
                  Comp = object.getString("Value");
            } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  Log.d("JSONParser => parseUserDetails", e.getMessage());
            }
            return Comp;
      }
 
      public String parseTrainLine(JSONObject object)
      {
    	  String Comp = "False";
            try {
                  Comp = object.getString("Value");
            } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  Log.d("JSONParser => parseUserDetails", e.getMessage());
            }
            return Comp;
      }
      
      
      public String parseRs(JSONObject object)
      {
    	  String Rs = "";
            try {
                  Rs = object.getString("Value");
            } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  Log.d("JSONParser => parseUserDetails", e.getMessage());
            }
            return Rs;
      }
      
      public String parsetic(JSONObject object)
      {
    	  String Rs = "";
            try {
                  Rs = object.getString("Value");
            } catch (JSONException e) {
                  // TODO Auto-generated catch block
                  Log.d("JSONParser => parseUserDetails", e.getMessage());
            }
            return Rs;
      }


	public String getneardata(JSONObject json) {
		String Rs = "";
        try {
              Rs = json.getString("Value");
        } catch (JSONException e) {
              // TODO Auto-generated catch block
              Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return Rs;
	}


	public String getpincodedata(JSONObject json) {
		String Rs = "";
        try {
              Rs = json.getString("Value");
        } catch (JSONException e) {
              // TODO Auto-generated catch block
              Log.d("JSONParser => parseUserDetails", e.getMessage());
        }
        return Rs;
	}


	public String loginuser(JSONObject json) {
		String p="parse";
		try {
			p=json.getString("Value");
		} catch (JSONException e) {
			p=e.getMessage();
		}
		return p;
	}
}