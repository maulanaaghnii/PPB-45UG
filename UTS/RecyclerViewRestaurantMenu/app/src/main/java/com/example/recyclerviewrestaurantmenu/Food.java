package com.example.recyclerviewrestaurantmenu;



import android.os.Parcel;
import android.os.Parcelable;

import java.nio.charset.Charset;

public class Food implements Parcelable {
    private String name;
    private String detail;
    private String price;
    private int photo;

    public Food(){

    }

    protected Food(Parcel in) {
        name = in.readString();
        detail = in.readString();
        photo = in.readInt();
        price = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(detail);
        parcel.writeInt(photo);
        parcel.writeString(price);
    }

}
