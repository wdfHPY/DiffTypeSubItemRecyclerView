package top.wangdf.difftypesubitemrecyclerview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.view.marginStart

/**
 * @time $ $
 * @author WDF
 * @description 待选设备设备列表列表子项类型二
 */
class ToBeSelectedTypeSix : View {

    private var itemText: String = ""
    private var itemTextSize: Int = -1
    private var itemTextColor: Int = -1
    private var itemTextMarginStart: Int = -1
    private var itemTextMarginTop: Int = -1
    private var itemButtonMarginStart: Int = -1
    private var itemButtonMarginTop: Int = -1
    private var itemButtonColor: Int = -1
    private var itemButtonBorderLength: Int = -1
    private var itemSelectAllTextMarginStart: Int = -1
    private var itemSelectAllTextSize: Int = -1
    private var itemSelectAllTextColor: Int = -1
    lateinit var listener: ItemViewClickListener

    private lateinit var mDashLinePaint: Paint //虚线绘笔

    private lateinit var mTextPaint: Paint //文字绘笔

    private lateinit var mControlBtnPaint: Paint // 控制按钮绘笔

    private lateinit var mSelectAllTextPaint: Paint //全选文字绘笔

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
                R.styleable.ToBeSelectedTypeFive,
                defStyleAttr,
                0
            )
            type.let {
                for (i in 0..it.indexCount) {
                    when (val temp = it.getIndex(i)) {
                        R.styleable.ToBeSelectedTypeFive_itemFiveText -> itemText =
                            it.getString(temp).toString()
                        R.styleable.ToBeSelectedTypeFive_itemFiveTextSize -> itemTextSize =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveTextColor -> itemTextColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveTextMarginStart -> itemTextMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveTextMarginTop -> itemTextMarginTop =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveButtonMarginStart -> itemButtonMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveButtonColor -> itemButtonColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveButtonLength-> itemButtonBorderLength =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveButtonMarginTop -> itemButtonMarginTop =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveSelectAllTextMarginStart -> itemSelectAllTextMarginStart =
                            it.getDimensionPixelSize(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveSelectAllTextColor -> itemSelectAllTextColor =
                            it.getColor(temp, -1)
                        R.styleable.ToBeSelectedTypeFive_itemFiveSelectAllTextSize -> itemSelectAllTextSize =
                            it.getDimensionPixelSize(temp, -1)
                    }
                }
            }
            initTextPaint()
            initDashLinePaint()
            initButtonPaint()
            initSelectAllPaint()
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

    private fun initSelectAllPaint() {
        mSelectAllTextPaint = Paint()
        mSelectAllTextPaint.isAntiAlias = true
        mSelectAllTextPaint.isDither = true
        mSelectAllTextPaint.color = itemSelectAllTextColor
        mSelectAllTextPaint.style = Paint.Style.FILL_AND_STROKE
        mSelectAllTextPaint.textSize = itemSelectAllTextSize.toFloat()
        mSelectAllTextPaint.textAlign = Paint.Align.LEFT //左对齐
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawDashLine(canvas)
        drawText(canvas)
        drawBtnBorder(canvas)
        drawSelectText(canvas)
    }

    private fun drawBtnBorder(canvas: Canvas?) {

        canvas?.drawLine(
            itemButtonMarginStart.toFloat(),
            itemButtonMarginTop.toFloat(),
            (itemButtonMarginStart + itemButtonBorderLength).toFloat(),
            itemButtonMarginTop.toFloat(),
            mControlBtnPaint
        )
        canvas?.drawLine(
            itemButtonMarginStart.toFloat(),
            itemButtonMarginTop.toFloat(),
            itemButtonMarginStart.toFloat(),
            (itemButtonMarginTop + itemButtonBorderLength).toFloat(),
            mControlBtnPaint
        )
        canvas?.drawLine(
            itemButtonMarginStart.toFloat(),
            (itemButtonMarginTop + itemButtonBorderLength).toFloat(),
            (itemButtonMarginStart + itemButtonBorderLength).toFloat(),
            (itemButtonMarginTop + itemButtonBorderLength).toFloat(),
            mControlBtnPaint
        )
        canvas?.drawLine(
            (itemButtonMarginStart + itemButtonBorderLength).toFloat(),
            itemButtonMarginTop.toFloat(),
            (itemButtonMarginStart + itemButtonBorderLength).toFloat(),
            (itemButtonMarginTop + itemButtonBorderLength).toFloat(),
            mControlBtnPaint
        )
        canvas?.drawLine(
            (itemButtonMarginStart + (itemButtonBorderLength / 4) * 1).toFloat(),
            height / 2F,
            (itemButtonMarginStart + (itemButtonBorderLength / 4) * 3).toFloat(),
            height / 2F,
            mControlBtnPaint
        )
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
            itemTextSize / 3.3F,
            height / 2F
        )
        //设置终点
        mPath.lineTo(
            itemButtonMarginStart - 1F,
            height / 2F
        )
        canvas?.drawPath(
            mPath,
            mDashLinePaint
        )
        //重新设置起点
        mPath.moveTo(
            itemButtonMarginStart + itemButtonBorderLength / 2F,
            (itemButtonMarginTop + itemButtonBorderLength).toFloat()
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

    private fun drawSelectText(canvas: Canvas?) {
        canvas?.drawText(
            "全选",
            itemSelectAllTextMarginStart - marginStart.toFloat(),
            (height  + itemSelectAllTextSize) / 2.2F,
            mSelectAllTextPaint
        )
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                //判断x轴上的坐标的范围是否合乎范围.比绘制的字体范围要稍大一些.左/右边分别多出0.6个TextSize.
                if (event.x >= (itemSelectAllTextMarginStart - marginStart - itemSelectAllTextSize * 0.6) && event.x <= (itemSelectAllTextMarginStart - marginStart + 2.6F * itemSelectAllTextSize)) {
                    listener.selectClick()
                    return true
                } else if ((event.x >= (itemButtonMarginStart - itemButtonBorderLength * 0.5) && event.x <= (itemButtonMarginStart + itemButtonBorderLength * 1.5)) &&
                    (event.y >= (itemButtonMarginTop - itemButtonBorderLength * 0.5) && event.y <= (itemButtonMarginTop + itemButtonBorderLength * 1.5))
                ) {
                    listener.iconClick()
                    return true
                } else {
                    return false
                }
            }
        }
        return super.onTouchEvent(event)
    }
}
