package top.wangdf.difftypesubitemrecyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.wangdf.difftypesubitemrecyclerview.databinding.PopMenuBinding

class PopUpMenuWithRecycleViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_menu_with_recycle_view)
        recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.adapter = PopUpMenuAdapter(prepareDataForText(), this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

fun prepareDataForText(): MutableList<Model> {
    return mutableListOf(
        Model("str1"),
        Model("str2"),
        Model("str3"),
        Model("str4"),
        Model("str5"),
        Model("str6"),
        Model("str7"),
        Model("str8"),
        Model("str9"),
        Model("str10"),
        Model("str11"),
        Model("str12"),
        Model("str13"),
        Model("str14"),
        Model("str15"),
        Model("str16"),
        Model("str17"),
        Model("str18"),
        Model("str19"),
        Model("str20"),
    )
}

class PopUpMenuAdapter(var list: MutableList<Model>, var context: Context): RecyclerView.Adapter<PopMenuHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopMenuHolder {
        return PopMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            PopMenuHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PopMenuHolder, position: Int) {
        holder.bindView(context, list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class PopMenuHolder(var binding: PopMenuBinding): RecyclerView.ViewHolder(binding.root) {
    fun bindView(context: Context, item: Model) {
        binding.tv.text = item.str
        binding.tv.setOnClickListener {
            showMenu(context, it, R.menu.context_menu)
        }
    }

    private fun showMenu(context: Context, v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        // Show the popup menu.
        popup.show()
    }
}

data class Model(
    var str: String
)