package top.wangdf.difftypesubitemrecyclerview

import android.content.Intent
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

        findViewById<ToBeSelectedTypeFour>(R.id.itemCusFour).listener = object : ItemViewClickListener {
            override fun selectClick() {
                Toast.makeText(this@MainActivity, "全选按钮被点击", Toast.LENGTH_SHORT).show()
            }

            override fun iconClick(): () -> Unit {
                Toast.makeText(this@MainActivity, "展开按钮被点击", Toast.LENGTH_SHORT).show()
                return super.iconClick()
            }
        }

        findViewById<ToBeSelectedTypeFive>(R.id.itemCusFive).listener = object : ItemViewClickListener {
            override fun selectClick() {
                Toast.makeText(this@MainActivity, "全选按钮被点击", Toast.LENGTH_SHORT).show()
            }

            override fun iconClick(): () -> Unit {
                Toast.makeText(this@MainActivity, "展开按钮被点击", Toast.LENGTH_SHORT).show()
                return super.iconClick()
            }
        }

        findViewById<ToBeSelectedTypeSix>(R.id.itemCusSix).listener = object : ItemViewClickListener {
            override fun selectClick() {
                Toast.makeText(this@MainActivity, "全选按钮被点击", Toast.LENGTH_SHORT).show()
            }

            override fun iconClick(): () -> Unit {
                Toast.makeText(this@MainActivity, "展开按钮被点击", Toast.LENGTH_SHORT).show()
                return super.iconClick()
            }
        }

        findViewById<ToBeSelectedTypeSeven>(R.id.itemCusSeven).listener = object : ItemViewClickListener {
            override fun selectClick() {
                Toast.makeText(this@MainActivity, "全选按钮被点击", Toast.LENGTH_SHORT).show()
            }

            override fun iconClick(): () -> Unit {
                Toast.makeText(this@MainActivity, "展开按钮被点击", Toast.LENGTH_SHORT).show()
                return super.iconClick()
            }
        }
    }
}