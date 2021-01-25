package top.wangdf.difftypesubitemrecyclerview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @time $ $
 * @author WDF
 * @description 待选设备设备列表列表子项类型二
 */
class ToBeSelectedTypeThree : View {

    private var itemText: String = ""
    private var itemTextSize: Int = -1
    private var itemTextColor: Int = -1
    private var itemTextMarginStart: Int = -1
    private var itemTextMarginTop: Int = -1

    private lateinit var mDashLinePaint: Paint //虚线绘笔

    private lateinit var mTextPaint: Paint //文字绘笔

    private lateinit var mPath: Path //DashLine Path原语

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }


    private fun init(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        var type: TypedArray? = null
        try {
            type = context.obtainStyledAttributes(
                attrs,
                R.styleable.ToBeSelectedTypeTwo,
                defStyleAttr,
                0
            )
            type.let {
                for (i in 0..it.indexCount) {
                    when (val temp = it.getIndex(i)) {
                        R.styleable.ToBeSelectedTypeTwo_itemText -> itemText =
                            it.getString(temp).toString()
                        R.styleable.ToBeSelectedTypeTwo_itemTextSize -> itemTextSize =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeTwo_itemTextColor -> itemTextColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeTwo_itemTextMarginStart -> itemTextMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeTwo_itemTextMarginTop -> itemTextMarginTop =
                            it.getDimensionPixelSize(temp, -1)
                    }
                }
            }
            initTextPaint()
            initDashLinePaint()
        } finally {
            type?.recycle()
        }
    }

    private fun initTextPaint() {
        mTextPaint = Paint()
        mTextPaint.isAntiAlias = true
        mTextPaint.isDither = true
        mTextPaint.color = itemTextColor
        mTextPaint.style = Paint.Style.FILL_AND_STROKE
        mTextPaint.textSize = itemTextSize.toFloat()
        mTextPaint.textAlign = Paint.Align.LEFT //左对齐
    }

    private fun initDashLinePaint() {
        mDashLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mDashLinePaint.style = Paint.Style.STROKE
        mDashLinePaint.isAntiAlias = true
        mDashLinePaint.strokeWidth = 1F
        mDashLinePaint.color = Color.parseColor("#4d7aad")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawDashLine(canvas)
        drawText(canvas)
    }

    private fun drawText(canvas: Canvas?) {
        canvas?.drawText(
            itemText,
            itemTextMarginStart.toFloat(),
            (itemTextMarginTop + itemTextSize).toFloat(),
            mTextPaint
        )
    }

    private fun drawDashLine(canvas: Canvas?) {
        val length = height / 12F
        mDashLinePaint.pathEffect = DashPathEffect(floatArrayOf(length, length), 0F)
        //使用 Path 原语来绘制虚线
        mPath = Path()
        //设置起始点
        mPath.moveTo(
            itemTextSize / 3.3F,
            0F
        )
        //设置终点
        mPath.lineTo(
            itemTextSize / 3.3F,
            height / 2F
        )
        canvas?.drawPath(
            mPath,
            mDashLinePaint
        )
        //重新设置起点
        mPath.moveTo(
            itemTextSize / 3.3F,
            height / 2F
        )
        //设置终点
        mPath.lineTo(
            itemTextMarginStart - 1F,
            height / 2F
        )
        canvas?.drawPath(
            mPath,
            mDashLinePaint
        )
    }
}
