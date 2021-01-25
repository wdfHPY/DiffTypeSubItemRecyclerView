package top.wangdf.difftypesubitemrecyclerview

/**
 * @time 2021-01-25
 * @author wdf
 * @description RecycleView中选项事件监听器
 */
interface ItemViewClickListener {

    //当item的[全选]按钮被点击时
    fun selectClick()

    //展开/收缩icon被点击
    fun iconClick() = {}
}