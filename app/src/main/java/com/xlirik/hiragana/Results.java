package com.xlirik.hiragana;

public class Results {
    private int _id;
    private String result;
    private String _time;

    public Results(){
    }

    public Results(int id, String result, String _time){
        this._id = id;
        this.result = result;
        this._time = _time;
    }

    public Results(String result, String _time){
        this.result = result;
        this._time = _time;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getResult(){
        return this.result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getTime(){
        return this._time;
    }

    public void setTime(String _time){
        this._time = _time;
    }
}
