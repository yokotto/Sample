//値段設定の情報を持つクラス

package model;

import java.util.*;
import java.io.*;

public class BeanInfo implements Serializable {
    //価格情報に関する変数を定義
    private double price1, price2, totalprice;
    private String type;
    
    //getterの作成
    public double getPrice1() { return price1; }
    public double getPrice2() { return price2; }
    public double getTotalprice() { return totalprice; }
    public String getType() {return type; }
    
    //setterの作成
    public void setPrice1(double price1) { this.price1 = price1; }
    public void setPrice2(double price2) { this.price2 = price2; }
    public void setTotalprice(double totalprice) { this.totalprice = totalprice; }
    public void setType(String type) { this.type = type; }
}