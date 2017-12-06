package com.zuckerteam.model.muasam;

import java.io.Serializable;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class DoDungChoBaBau implements Serializable {
    private int id;
    private String tenDoDung;
    private String motaDoDung;

    public DoDungChoBaBau() {
    }

    public DoDungChoBaBau(int id, String tenDoDung, String motaDoDung) {
        this.id = id;
        this.tenDoDung = tenDoDung;
        this.motaDoDung = motaDoDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDoDung() {
        return tenDoDung;
    }

    public void setTenDoDung(String tenDoDung) {
        this.tenDoDung = tenDoDung;
    }

    public String getMotaDoDung() {
        return motaDoDung;
    }

    public void setMotaDoDung(String motaDoDung) {
        this.motaDoDung = motaDoDung;
    }
}
