package com.example.directing;

public class DataList {

    private int id;
    private byte[] image ;
    private String name;

    public DataList(int id, String name, byte[] image){

        this.id=id;
        this.name=name;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

