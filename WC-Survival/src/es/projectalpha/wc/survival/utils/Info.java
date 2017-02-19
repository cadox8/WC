package es.projectalpha.wc.survival.utils;

import java.util.ArrayList;

public class Info {

    private ArrayList<String> msgs;
    private int id;

    public Info(){
        msgs = new ArrayList<>();
        id = 0;
    }

    public void init(){

    }

    public String getInfoMsg(){
        id++;

        if (id == msgs.size()) id = 0;
        return "";
    }
}
