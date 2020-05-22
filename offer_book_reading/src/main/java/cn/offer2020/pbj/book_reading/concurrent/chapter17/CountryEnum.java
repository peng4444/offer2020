package cn.offer2020.pbj.book_reading.concurrent.chapter17;
/* *
 * 功能描述:枚举的使用
 * @param:
 * @return:
 * @auther: pbj
 * @date: 2020/5/22 14:32
 */
public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer retCode;
    private String retName;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetName() {
        return retName;
    }

    public void setRetName(String retName) {
        this.retName = retName;
    }

    CountryEnum(Integer retCode, String retName) {
        this.retCode = retCode;
        this.retName = retName;
    }

    public static CountryEnum foreach_CountryEnum(int index) {
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element: myArray
             ) {
            if(index==element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
