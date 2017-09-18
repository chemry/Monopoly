package gui;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CHENG Mingrui on 2017/9/18.
 */
public class status {
    static String[] stateDescribe = {"welcome", "single", "multi", "playing"};
    private Map<String, Integer> state = new HashMap<String, Integer>();
    private String preStatus;

    public status(){
        state.put(stateDescribe[0], 1);
        for(int i = 1; i < stateDescribe.length; i++)
            state.put(stateDescribe[i], 0);
        preStatus = "None";
    }

    public String getStatus(){
        return "";
    }
}
