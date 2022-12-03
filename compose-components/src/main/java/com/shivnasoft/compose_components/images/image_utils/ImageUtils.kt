package com.shivnasoft.compose_components.images.image_utils

import android.R
import android.R.attr.defaultValue
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.ExifInterface
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Size
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.blankj.utilcode.util.UriUtils
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.destination
import java.io.File
import java.io.InputStream

const val IMAGE_JPEG_SUFFIX = ".jpg"
const val IMAGE_MIME_TYPE = "image/jpeg"
const val EMPTY_IMAGE_URI = ""

object ImageUtils {
    fun Context.getImageDimensionsFromUri(uri: Uri): Pair<Int, Int> {
        val inputStream: InputStream = this.contentResolver.openInputStream(uri)!!
        val exif = ExifInterface(inputStream)

        val width = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, defaultValue)
        val height = exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, defaultValue)

        return Pair(width, height)
    }

    fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {

/*    val fOut = FileOutputStream(this)

    bitmap.compress(format, 0, fOut)
    fOut.flush()
    fOut.close()*/

        outputStream().use { out ->
            bitmap.compress(format, quality, out)
            out.flush()
        }
    }

@RequiresApi(Build.VERSION_CODES.Q)
suspend fun Context.compressImage(imageUri: Uri, destFolder: String, filePrefix: String): Uri {
    var newUri: Uri = Uri.EMPTY
    try {
        val originalFile: File = UriUtils.uri2File(imageUri)
        val timestamp = System.currentTimeMillis()
        val directory =
            File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), destFolder)
        if (!directory.exists()) directory.mkdirs()

        val newFile = File(
            directory.absolutePath,"${filePrefix}_${timestamp}" + ".jpg"
        )

        newFile.createNewFile()

        Compressor.compress(this, originalFile) {
            default(format = Bitmap.CompressFormat.JPEG)
            destination(newFile)
        }

        newUri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".fileprovider",
            newFile
        )
    } catch (e: Exception) {
        Toast.makeText(this, "Unable to process your image.", Toast.LENGTH_SHORT).show()
    }

    return newUri
}

    @RequiresApi(Build.VERSION_CODES.Q)
    fun Context.createImageThumbnail(uri: Uri, destFolder: String, filePrefix: String): Uri {
        var itemThumbnailUri: Uri = Uri.EMPTY
        val timestamp = System.currentTimeMillis()
        val itemThumbnailImageDirectory =
            File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), destFolder)

        if (!itemThumbnailImageDirectory.exists()) itemThumbnailImageDirectory.mkdirs()

        val itemThumbnailFile = File(
            itemThumbnailImageDirectory.absolutePath,"${filePrefix}_${timestamp}" + ".jpg"
        )
        itemThumbnailFile.createNewFile()

        val itemThumbnailBitmap = extractItemThumbnail(uri)
        itemThumbnailFile.writeBitmap(itemThumbnailBitmap!!, Bitmap.CompressFormat.PNG, 0)

        itemThumbnailUri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.fileprovider",
            itemThumbnailFile
        )

        return itemThumbnailUri
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun extractItemThumbnail(uri: Uri): Bitmap? {
        val file = UriUtils.uri2File(uri)
        val thumbnailBitmap = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            ThumbnailUtils.createImageThumbnail(
                file.toString(),
                MediaStore.Images.Thumbnails.MINI_KIND
            )
        } else {
            ThumbnailUtils.createImageThumbnail(file!!, Size(100, 100), null)
        }
        return thumbnailBitmap
    }

    fun Uri.deleteUriImage(context: Context) {
        context.contentResolver.delete(this, null, null)
    }

    fun Context.getCamMediaUri(camImageDirectory: String): Uri {
        var intent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(
                    MediaStore.MediaColumns.DISPLAY_NAME,
                    "${System.currentTimeMillis()}$IMAGE_JPEG_SUFFIX"
                )
                put(MediaStore.MediaColumns.MIME_TYPE, IMAGE_MIME_TYPE)
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + camImageDirectory)
            }
            val uri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )!!

            intent?.putExtra(MediaStore.EXTRA_OUTPUT, uri)

            uri
        } else {
            val directory =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), camImageDirectory)
            if (!directory.exists()) {
                directory.mkdir()
            }
            /*val directory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            if (!directory.exists()) {
                directory.mkdir()
            }*/
            val file = File.createTempFile(
                "${System.currentTimeMillis()}",
                IMAGE_JPEG_SUFFIX,
                directory
            )
            intent?.putExtra(
                MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    file
                )
            )

            FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.fileprovider",
                file
            )
        }
    }

    fun Uri.getImageDimensions(context: Context): Pair<Int, Int> {
        val inputStream: InputStream = context.contentResolver.openInputStream(this)!!
        val exif = ExifInterface(inputStream)

        val width = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, R.attr.defaultValue)
        val height = exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, R.attr.defaultValue)

        return Pair(width, height)
    }
}