package com.mtt.thethiem.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("IdALbum")
@Expose
private String idALbum;
@SerializedName("TenAlbum")
@Expose
private String tenAlbum;
@SerializedName("TenCasiAlbum")
@Expose
private String tenCasiAlbum;
@SerializedName("HinhAlbum")
@Expose
private String hinhAlbum;

public String getIdALbum() {
return idALbum;
}

public void setIdALbum(String idALbum) {
this.idALbum = idALbum;
}

public String getTenAlbum() {
return tenAlbum;
}

public void setTenAlbum(String tenAlbum) {
this.tenAlbum = tenAlbum;
}

public String getTenCasiAlbum() {
return tenCasiAlbum;
}

public void setTenCasiAlbum(String tenCasiAlbum) {
this.tenCasiAlbum = tenCasiAlbum;
}

public String getHinhAlbum() {
return hinhAlbum;
}

public void setHinhAlbum(String hinhAlbum) {
this.hinhAlbum = hinhAlbum;
}

}