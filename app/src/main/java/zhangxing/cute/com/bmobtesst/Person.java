package zhangxing.cute.com.bmobtesst;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhangxing on 2016/9/27.
 */
public class Person extends BmobObject {
    private  String name;
    private  String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
