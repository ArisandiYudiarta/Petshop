package com.example.petshop.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AnimalsModel implements Parcelable {
    private int id;
    private String pet_name;
    private String desc;
    private int pet_age;
    private String pet_category;
    private String pet_gender;
    private String pet_photo;
    private float pet_height;
    private float pet_weight;

    public AnimalsModel(int id, String pet_name, String desc, int pet_age, String pet_category, String pet_gender, String pet_photo, float pet_height, float pet_weight) {
        this.id = id;
        this.pet_name = pet_name;
        this.desc = desc;
        this.pet_age = pet_age;
        this.pet_category = pet_category;
        this.pet_gender = pet_gender;
        this.pet_photo = pet_photo;
        this.pet_height = pet_height;
        this.pet_weight = pet_weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPet_age() {
        return pet_age;
    }

    public void setPet_age(int pet_age) {
        this.pet_age = pet_age;
    }

    public String getPet_category() {
        return pet_category;
    }

    public void setPet_category(String pet_category) {
        this.pet_category = pet_category;
    }

    public String getPet_gender() {
        return pet_gender;
    }

    public void setPet_gender(String pet_gender) {
        this.pet_gender = pet_gender;
    }

    public String getPet_photo() {
        return pet_photo;
    }

    public void setPet_photo(String pet_photo) {
        this.pet_photo = pet_photo;
    }

    public float getPet_height() {
        return pet_height;
    }

    public void setPet_height(float pet_height) {
        this.pet_height = pet_height;
    }

    public float getPet_weight() {
        return pet_weight;
    }

    public void setPet_weight(float pet_weight) {
        this.pet_weight = pet_weight;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.pet_name);
        dest.writeString(this.desc);
        dest.writeInt(this.pet_age);
        dest.writeString(this.pet_category);
        dest.writeString(this.pet_gender);
        dest.writeString(this.pet_photo);
        dest.writeFloat(this.pet_height);
        dest.writeFloat(this.pet_weight);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.pet_name = source.readString();
        this.desc = source.readString();
        this.pet_age = source.readInt();
        this.pet_category = source.readString();
        this.pet_gender = source.readString();
        this.pet_photo = source.readString();
        this.pet_height = source.readFloat();
        this.pet_weight = source.readFloat();
    }

    protected AnimalsModel(Parcel in) {
        this.id = in.readInt();
        this.pet_name = in.readString();
        this.desc = in.readString();
        this.pet_age = in.readInt();
        this.pet_category = in.readString();
        this.pet_gender = in.readString();
        this.pet_photo = in.readString();
        this.pet_height = in.readFloat();
        this.pet_weight = in.readFloat();
    }

    public static final Parcelable.Creator<AnimalsModel> CREATOR = new Parcelable.Creator<AnimalsModel>() {
        @Override
        public AnimalsModel createFromParcel(Parcel source) {
            return new AnimalsModel(source);
        }

        @Override
        public AnimalsModel[] newArray(int size) {
            return new AnimalsModel[size];
        }
    };
}
