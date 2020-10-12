package pl.filters

import java.awt.image.BufferedImage

import scala.collection.parallel.CollectionConverters.RangeIsParallelizable

class AsyncMedianFilter(val windowWidth: Int = 10, val windowHeight: Int = 10) {
  private val edgeX: Int = windowWidth / 2
  private val edgeY: Int = windowHeight / 2

  def medianFilter(bufferedImage: BufferedImage) : BufferedImage = {
    val imageWidth: Int = bufferedImage.getWidth()
    val imageHeight: Int = bufferedImage.getHeight()

    val bufferedOutputImage: BufferedImage =
      new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)

    for (x <- (edgeX until imageWidth - edgeX).par) {
      for (y <- (edgeY until imageHeight - edgeY).par) {
        bufferedOutputImage.setRGB(x, y, medianOfWindow(bufferedImage, x, y))
      }
    }

    bufferedOutputImage
  }

  private def medianOfWindow(bufferedImage: BufferedImage, startX: Int, startY: Int): Int = {
    val window = new Array[Int](this.windowWidth * this.windowHeight)
    bufferedImage.getRGB(startX - edgeX, startY - edgeY, windowWidth, windowHeight, window, 0, windowWidth)

    window.sortInPlace()

    window(windowHeight * windowWidth / 2)
  }
}
