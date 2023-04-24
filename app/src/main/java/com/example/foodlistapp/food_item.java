package com.example.foodlistapp;

import android.os.Parcel;
import android.os.Parcelable;

public class food_item implements Parcelable {

    private int image_id;
    private int food_price;
    private String food_cname;
    private String food_ename;
    private int food_num;

    public food_item(int image_id, int food_price, String food_cname, String food_ename, int food_num) {
        this.image_id = image_id;
        this.food_price = food_price;
        this.food_cname = food_cname;
        this.food_ename = food_ename;
        this.food_num = food_num;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getFood_price() {
        return food_price;
    }

    public void setFood_price(int food_price) {
        this.food_price = food_price;
    }

    public String getFood_cname() {
        return food_cname;
    }

    public void setFood_cname(String food_cname) {
        this.food_cname = food_cname;
    }

    public String getFood_ename() {
        return food_ename;
    }

    public void setFood_ename(String food_ename) {
        this.food_ename = food_ename;
    }

    public int getFood_num() {
        return food_num;
    }

    public void setFood_num(int food_num) {
        this.food_num = food_num;
    }

    protected food_item(Parcel in) {
        image_id = in.readInt();
        food_price = in.readInt();
        food_cname = in.readString();
        food_ename = in.readString();
        food_num = in.readInt();
    }

    public static final Creator<food_item> CREATOR = new Creator<food_item>() {
        @Override
        public food_item createFromParcel(Parcel in) {
            return new food_item(in);
        }

        @Override
        public food_item[] newArray(int size) {
            return new food_item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image_id);
        dest.writeInt(food_price);
        dest.writeString(food_cname);
        dest.writeString(food_ename);
        dest.writeInt(food_num);
    }
}
