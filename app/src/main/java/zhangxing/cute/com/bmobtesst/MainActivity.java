package zhangxing.cute.com.bmobtesst;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {
     private EditText nameEt,ageEt,queryEt,dataEt,updateEt,deleteEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "1d84ba15a417ab29fdaea307d5574b9d");
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        nameEt = (EditText) findViewById(R.id.nameEt);
        ageEt = (EditText) findViewById(R.id.ageEt);
        queryEt = (EditText) findViewById(R.id.query);
        dataEt = (EditText) findViewById(R.id.data);
        updateEt = (EditText) findViewById(R.id.updateName);
        deleteEt = (EditText) findViewById(R.id.delete);
    }

    /**
     * 提交数据
     * @param v
     */
    public void submitData(View v){
        String name = nameEt.getText().toString();
        String age =ageEt.getText().toString();
        if(name.equals("")||age.equals("")){
          return ;
        }
        Person p1 = new Person();
        p1.setName(name);
        p1.setAge(age);
        p1.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(null == e){
                    Toast.makeText(MainActivity.this,"数据上传成功，ObjectId:"+s,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"数据上传失败，错误信息："+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 获取数据
     * @param v
     */
    public void captureData(View v){
       String str = queryEt.getText().toString();
        if(str.equals("")){
            return;
        }
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.addWhereEqualTo("name",str);
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                try {
                if(!list.isEmpty()){
                    String tmp = "";
                    for (Person pl:list) {
                        tmp +=pl.getName()+","+pl.getAge();
                    }
                    Toast.makeText(MainActivity.this,"查询成功，信息为："+tmp,Toast.LENGTH_SHORT).show();
                }

            }catch (IllegalStateException i){
                    i.printStackTrace();
                    Toast.makeText(MainActivity.this,"查询失败，错误信息为："+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    /**
     * 修改数据
     */
public void updateData(View v){
    String id = dataEt.getText().toString();
    String name = updateEt.getText().toString();
    Person p = new Person();
    p.setName(name);
    p.update(id, new UpdateListener() {
        @Override
        public void done(BmobException e) {
            if(null == e){
                Toast.makeText(MainActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"修改失败，错误信息为："+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    });

}
    /**
     * 删除数据
     */
 public void deleteData(View v){
     String id = deleteEt.getText().toString();
     Person p =new Person();
     p.setObjectId(id);
     p.delete(new UpdateListener() {
         @Override
         public void done(BmobException e) {
             if(null == e){
                 Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(MainActivity.this,"删除失败，错误信息为："+e.getMessage(),Toast.LENGTH_SHORT).show();
             }
         }
     });
 }
}