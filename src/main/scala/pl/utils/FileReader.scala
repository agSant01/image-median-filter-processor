package pl.utils

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

class FileReader(val path: String) {
  private val file: File = new File(this.path)
  private val image: BufferedImage = ImageIO.read(this.file)
  private var savedFileName: String = ""

  def getBufferedImage: BufferedImage = {
    this.image
  }

  def saveImage(imageToSave: BufferedImage, appendToName: String): String = {
    this.savedFileName = "%s%s".format(this.file.getName
      .replace(".%s".format(getFileExtension), ""), appendToName)

    if(ImageIO.write(imageToSave, getFileExtension, new File(getSavedPath))) {
      getSavedPath
    } else {
      null
    }
  }

  def getSavedPath: String = {
    s"$getAbsoluteDirectory${this.savedFileName}-median-filter.$getFileExtension"
  }

  def getAbsoluteDirectory: String = {
    val idx = this.file.getAbsolutePath.lastIndexOf(File.separator)
    this.file.getAbsolutePath.substring(0, idx) + File.separator
  }

  def getFileExtension: String = {
    val idx = this.file.getName.lastIndexOf(".")

    if (idx < 0) {
      return ""
    }

    this.file.getName.substring(idx + 1)
  }
}
