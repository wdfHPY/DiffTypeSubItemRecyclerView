package top.wangdf.difftypesubitemrecyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


/**
 * @time $ $
 * @author WDF
 * @description 待选设备设备列表列表子项类型一
 */
class ToBeSelectedTypeOne : View {

    private var itemBackgroundColor: Int = -1
    private var itemMainText: String = ""
    private var itemMainTextSize: Int = -1
    private var itemMainTextColor: Int = -1
    private var itemMainTextMarginStart: Int = -1
    private var itemMainTextMarginTop: Int = -1
    private var itemSelectAllTextColor: Int = -1
    private var itemSelectAllTextSize: Int = -1
    private var itemSelectAllTextMarginStart: Int = -1
    lateinit var listener: ItemViewClickListener

    private lateinit var mPath: Path //DashLine Path原语

    private lateinit var mContentTextPaint: Paint //内容主体文字绘笔

    private lateinit var mDashLinePaint: Paint //虚线绘笔

    private lateinit var mSelectAllTextPaint: Paint //全选主体文字绘笔

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
            type = context.obtainStyledAttributes(attrs, R.styleable.ToBeSelectedTypeOne, defStyleAttr, 0)
            type.let {
                for (i in 0..it.indexCount) {
                    when (val temp = it.getIndex(i)) {
                        R.styleable.ToBeSelectedTypeOne_itemBackgroundColor -> itemBackgroundColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemMainText -> itemMainText =
                            it.getString(temp).toString()
                        R.styleable.ToBeSelectedTypeOne_itemMainTextSize -> itemMainTextSize =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemMainTextColor -> itemMainTextColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemMainTextMarginStart -> itemMainTextMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemMainTextMarginTop -> itemMainTextMarginTop =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemSelectAllTextColor -> itemSelectAllTextColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemSelectAllTextSize -> itemSelectAllTextSize =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeOne_itemSelectAllTMarginStart -> itemSelectAllTextMarginStart =
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
        mContentTextPaint = Paint()
        mContentTextPaint.isAntiAlias = true
        mContentTextPaint.isDither = true
        mContentTextPaint.color = itemMainTextColor
        mContentTextPaint.style = Paint.Style.FILL_AND_STROKE
        mContentTextPaint.textSize = itemMainTextSize.toFloat()
        mContentTextPaint.textAlign = Paint.Align.LEFT //左对齐

        mSelectAllTextPaint = Paint()
        mSelectAllTextPaint.isAntiAlias = true
        mSelectAllTextPaint.isDither = true
        mSelectAllTextPaint.color = itemSelectAllTextColor
        mSelectAllTextPaint.style = Paint.Style.FILL_AND_STROKE
        mSelectAllTextPaint.textSize = itemSelectAllTextSize.toFloat()
        mSelectAllTextPaint.textAlign = Paint.Align.LEFT //左对齐
    }

    private fun initDashLinePaint() {
        mDashLinePaint = Paint(ANTI_ALIAS_FLAG)
        mDashLinePaint.style = Paint.Style.STROKE
        mDashLinePaint.isAntiAlias = true
        mDashLinePaint.strokeWidth = 1F
        mDashLinePaint.color = Color.parseColor("#4d7aad")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawContentText(canvas)
        drawDashLine(canvas)
        drawSelectText(canvas)
    }

    private fun drawSelectText(canvas: Canvas?) {
        canvas?.drawText(
            "全选",
            itemSelectAllTextMarginStart.toFloat(),
            (itemMainTextMarginTop + itemMainTextSize).toFloat(),
            mSelectAllTextPaint
        )
    }

    private fun drawDashLine(canvas: Canvas?) {
        val length = (height - (itemMainTextMarginTop + itemMainTextSize)) / 4F
        mDashLinePaint.pathEffect = DashPathEffect(floatArrayOf(length, length), 0F)
        //使用 Path 原语来绘制虚线
        mPath = Path()
        //设置起始点
        mPath.moveTo(
            (itemMainTextMarginStart + itemMainTextSize / 3.3).toFloat(),
            itemMainTextMarginTop + itemMainTextSize.toFloat()
        )
        //设置终点
        mPath.lineTo(
            (itemMainTextMarginStart + itemMainTextSize / 3.3).toFloat(),
            height.toFloat()
        )
        canvas?.drawPath(
            mPath,
            mDashLinePaint
        )
    }

    private fun drawContentText(canvas: Canvas?) {
        canvas?.drawText(
            itemMainText,
            itemMainTextMarginStart.toFloat(),
            (itemMainTextMarginTop + itemMainTextSize).toFloat(),
            mContentTextPaint
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                //进判断x轴上的坐标的范围是否合乎范围.比绘制的字体范围要稍大一些.左/右边分别多出0.6个TextSize.
                if (event.x >= (itemSelectAllTextMarginStart - itemSelectAllTextSize * 0.6) && event.x <= (itemSelectAllTextMarginStart + 2.6F * itemSelectAllTextSize)) {
                    listener.selectClick()
                    return true
                } else {
                    return false
                }
            }
        }
        return super.onTouchEvent(event)
    }
}