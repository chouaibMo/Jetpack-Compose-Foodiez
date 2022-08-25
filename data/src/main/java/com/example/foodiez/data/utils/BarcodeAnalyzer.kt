package com.example.foodiez.data.utils

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

@SuppressLint("UnsafeOptInUsageError")
class BarcodeAnalyzer(private val onBarcodeDetected: (barcode: Barcode?) -> Unit) : ImageAnalysis.Analyzer {

    override fun analyze(image: ImageProxy) {
        image.image?.let {
            val imageValue = InputImage.fromMediaImage(it, image.imageInfo.rotationDegrees)
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_EAN_13)
                .build()
            val scanner = BarcodeScanning.getClient(options)
            scanner.process(imageValue)
                .addOnCompleteListener { barcodes ->
                    onBarcodeDetected(barcodes.result.firstOrNull())
                    image.image?.close()
                    image.close()
                }
                .addOnFailureListener { failure ->
                    failure.printStackTrace()
                    image.image?.close()
                    image.close()
                }
        }
    }
}