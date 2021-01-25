package top.wangdf.difftypesubitemrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ToBeSelectedTypeOne>(R.id.typeOne).listener = object : ItemViewClickListener {
            override fun selectClick() {
                Toast.makeText(this@MainActivity, "全选按钮被点击", Toast.LENGTH_SHORT).show()
            }
        }
    }
}