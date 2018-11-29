package com.example.fpm0322.myfirstapp.bean;

public class MenuItemBean {

    private String itemName;

    /**
     * 是否处于编辑状态
     */
    private boolean itemState = false;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemState() {
        return itemState;
    }

    public void setItemState(boolean itemState) {
        this.itemState = itemState;
    }

    public MenuItemBean(){

    }

    public MenuItemBean(String itemName,boolean itemState){
        this.itemName = itemName;
        this.itemState = itemState;
    }
}
