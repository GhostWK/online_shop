package ru.ncedu.onlineshop.wrappers;

public class WrapperKeyWord {

    private Long id;
    private String k;
    private String v;

    public WrapperKeyWord() {
    }

    public WrapperKeyWord(Long id, String k, String v) {
        this.id = id;
        this.k = k;
        this.v = v;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
