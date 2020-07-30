package com.mobi.sdk.overseasad.bean;

import java.util.List;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:48
 * @Dec 略
 */
public class AdBean {

    /**
     * style : 2
     * width : 300
     * height : 250
     * bdt : 4
     * fw : 0
     * ctype : 1
     * adid : 234
     * ad : PGRpdj4KICAgIDxkaXYgY2xhc3M9ImNvbnRhaW5lciIgc3R5bGU9IndpZHRoOjEwMCU7IGhlaWdodDoxMDAlO292ZXJmbG93OmhpZGRlbjsiPuS9oOWlvQogICAgICAgIDxpbWcgYWx0PSIiCiAgICAgICAgICAgICBzcmM9Imh0dHBzOi8vdGltZ3NhLmJhaWR1LmNvbS90aW1nP2ltYWdlJnF1YWxpdHk9ODAmc2l6ZT1iOTk5OV8xMDAwMCZzZWM9MTU5NjAyNTQ3NzE5MSZkaT04OTkzODJmODY4NjhkZDhhZDQxZGRlZTdjMWU3ZTk5ZiZpbWd0eXBlPTAmc3JjPWh0dHAlM0ElMkYlMkZwaWMyMy5uaXBpYy5jb20lMkYyMDEyMDkxNCUyRjEwOTIzNzg3XzE1NTMzNzY3MTEwOF8yLmdpZiIvPgogICAgPC9kaXY+CiAgICA8c2NyaXB0IHR5cGU9InRleHQvamF2YXNjcmlwdCI+CiAgICAgICAgKGZ1bmN0aW9uKHdpbmRvdyxkb2N1bWVudCl7dmFyIGdldEFsbEE9ZG9jdW1lbnQuZ2V0RWxlbWVudHNCeUNsYXNzTmFtZSgnY29udGFpbmVyJylbMF0uY2hpbGROb2Rlcztmb3IodmFyIGk9Z2V0QWxsQS5sZW5ndGgtMTtpPj0wO2ktLSl7Z2V0QWxsQVtpXS5hZGRFdmVudExpc3RlbmVyKCd0b3VjaHN0YXJ0JyxmdW5jdGlvbihldil7dmFyIG9JbWc9bmV3IEltYWdlKCk7b0ltZy5zcmM9IiJ9LGZhbHNlKX19KShGdW5jdGlvbigncmV0dXJuIHRoaXMnKSgpLGRvY3VtZW50KTsKCiAgICA8L3NjcmlwdD4KPC9kaXY+
     * img_track : ["https://www.baidu.com/?a=10"]
     * clk_track : ["https://www.baidu.com/?a=20"]
     * tact : {"pcache":60,"freq_time":60,"freq_count":60}
     */
    //1-native 2-banner 3-video
    private int style;
    private int width;
    private int height;
    private int bdt;
    private int fw;
    private int ctype;
    private String adid;
    private String ad;
    private TactBean tact;
    private List<String> imgTrack;
    private List<String> clkTrack;

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBdt() {
        return bdt;
    }

    public void setBdt(int bdt) {
        this.bdt = bdt;
    }

    public int getFw() {
        return fw;
    }

    public void setFw(int fw) {
        this.fw = fw;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public TactBean getTact() {
        return tact;
    }

    public void setTact(TactBean tact) {
        this.tact = tact;
    }

    public List<String> getImgTrack() {
        return imgTrack;
    }

    public void setImgTrack(List<String> imgTrack) {
        this.imgTrack = imgTrack;
    }

    public List<String> getClkTrack() {
        return clkTrack;
    }

    public void setClkTrack(List<String> clkTrack) {
        this.clkTrack = clkTrack;
    }

    public AdBean(int style, int width, int height, int bdt, int fw, int ctype,
                  String adid, String ad, TactBean tact, List<String> imgTrack,
                  List<String> clkTrack) {
        this.style = style;
        this.width = width;
        this.height = height;
        this.bdt = bdt;
        this.fw = fw;
        this.ctype = ctype;
        this.adid = adid;
        this.ad = ad;
        this.tact = tact;
        this.imgTrack = imgTrack;
        this.clkTrack = clkTrack;
    }

    public AdBean() {
    }
}
