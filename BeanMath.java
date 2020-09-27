//値段に関する処理を行うクラス

package model;

public class BeanMath {
    public void execute(BeanInfo beaninfo) {
        //合計金額を算出して設定
        double price1 = beaninfo.getPrice1();
        double price2 = beaninfo.getPrice2();
        double totalprice = price1 + price2;
        beaninfo.setTotalprice(totalprice);
        
        //税込金額から金額レベルを設定
        String type;
        if(totalprice < 2000){
            type = "安価";
        } else if(totalprice < 5000 && totalprice >= 2000 ){
            type = "普通";
        } else {
            type = "高価";
        }
        beaninfo.setType(type);
    }
}