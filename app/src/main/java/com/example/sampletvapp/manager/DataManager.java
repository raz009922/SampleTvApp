package com.example.sampletvapp.manager;

import com.example.sampletvapp.model.DataItem;
import com.example.sampletvapp.model.Token;

public class DataManager {
    private static final DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    private DataItem selectedDataItem = null;

    public DataItem getSelectedDataItem() {
        return selectedDataItem;
    }

    public void setSelectedDataItem(DataItem selectedDataItem) {
        this.selectedDataItem = selectedDataItem;
    }

    private Token token;

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
