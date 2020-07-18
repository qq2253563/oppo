package com.student.patient.activity

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.student.patient.R
import kotlinx.android.synthetic.main.activity_qrcode.*
import java.util.*


class QRCodeActivity : AppCompatActivity() {

    companion object{
        val URL = "url"
        val NAME = "name"
        fun newInstance(context: Context, url:String, name:String) {
            val intent = Intent(context, QRCodeActivity::class.java)
            intent.putExtra(URL,url)
            intent.putExtra(NAME,name)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        initView()
    }

    private fun initView() {
        qr_name.text = intent.getStringExtra(NAME)
        qr_title.setBackListener(null,this)
        Thread{
            val createQRImage = createQRImage(intent.getStringExtra(URL), dp2px(300f), dp2px(300f))
            qr_code.post {
                qr_code.setImageBitmap(createQRImage)
            }
        }.start()
    }

    fun dp2px(dpValue: Float): Int {
        return (0.5f + dpValue * Resources.getSystem()
            .displayMetrics.density).toInt()
    }

    fun createQRImage(content: String?, widthPix: Int, heightPix: Int): Bitmap? {
        try {
            if (TextUtils.isEmpty(content)) {
                return null
            }
            // 配置参数
            val hints = Hashtable<EncodeHintType, Any>()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            // 容错级别
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            // 设置空白边距的宽度
            hints[EncodeHintType.MARGIN] = 2 // default is 4

            // 图像数据转换，使用了矩阵转换
            val bitMatrix =
                QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints)
            val pixels = IntArray(widthPix * heightPix)
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (y in 0 until heightPix) {
                for (x in 0 until widthPix) {
                    if (bitMatrix[x, y]) {
                        pixels[y * widthPix + x] = -0x1000000
                    } else {
                        pixels[y * widthPix + x] = -0x1
                    }
                }
            }

            // 生成二维码图片的格式，使用RGB_565
            var bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.RGB_565)
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}