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
class ToBeSelectedTypeEight : View {

    private var itemText: String = ""
    private var itemTextSize: Int = -1
    private var itemTextColor: Int = -1
    private var itemTextMarginStart: Int = -1
    private var itemTextMarginTop: Int = -1
    private var itemButtonMarginStart: Int = -1
    private var itemButtonMarginTop: Int = -1
    private var itemButtonColor: Int = -1
    private var itemButtonBorderLength: Int = -1

    private lateinit var mDashLinePaint: Paint //虚线绘笔

    private lateinit var mTextPaint: Paint //文字绘笔

    private lateinit var mControlBtnPaint: Paint // 控制按钮绘笔

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
                R.styleable.ToBeSelectedTypeFour,
                defStyleAttr,
                0
            )
            type.let {
                for (i in 0..it.indexCount) {
                    when (val temp = it.getIndex(i)) {
                        R.styleable.ToBeSelectedTypeFour_itemFourText -> itemText =
                            it.getString(temp).toString()
                        R.styleable.ToBeSelectedTypeFour_itemFourTextSize -> itemTextSize =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourTextColor -> itemTextColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourTextMarginStart -> itemTextMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourTextMarginTop -> itemTextMarginTop =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourButtonMarginStart -> itemButtonMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourButtonColor -> itemButtonColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourButtonLength-> itemButtonBorderLength =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFour_itemFourButtonMarginTop -> itemButtonMarginTop =
                            it.getDimensionPixelSize(temp, -1)
                    }
                }
            }
            initTextPaint()
            initDashLinePaint()
            initButtonPaint()
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

    private fun initButtonPaint() {
        mControlBtnPaint = Paint()
        mControlBtnPaint.isAntiAlias = true
        mControlBtnPaint.isDither = true
        mControlBtnPaint.color = itemButtonColor
        mControlBtnPaint.style = Paint.Style.FILL_AND_STROKE
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
            (height  + itemTextSize) / 2.2F,
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
            height.toFloat()
        )
        canvas?.drawPath(
            mPath,
            mDashLinePaint
        )

        //重新设置起点
        mPath.moveTo(
            itemButtonMarginStart + itemButtonBorderLength / 2F,
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

        //重新设置起点
        mPath.moveTo(
            itemButtonMarginStart + itemButtonBorderLength / 2F,
            0F
        )
        mPath.lineTo(
            itemButtonMarginStart + itemButtonBorderLength / 2F,
            height.toFloat()
        )
        canvas?.drawPath(
            mPath,
            mDashLinePaint
        )
    }
}
