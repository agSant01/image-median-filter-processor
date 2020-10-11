package pl.filters

import java.awt.image.BufferedImage

class SyncMedianFilter(private val windowWidth: Int = 10, private val windowHeight: Int = 10) {
  private val edgeX: Int = windowWidth / 2
  private val edgeY: Int = windowHeight / 2

  def medianFilter(bufferedImage: BufferedImage) : BufferedImage = {
    val imageWidth: Int = bufferedImage.getWidth()
    val imageHeight: Int = bufferedImage.getHeight()

    val bufferedOutputImage =
      new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)

    val window = new Array[Int](windowWidth * windowHeight)

    for (x <- edgeX until imageWidth - edgeX) {
      for (y <- edgeY until imageHeight - edgeY) {
        bufferedImage.getRGB(x - edgeX, y - edgeY, windowWidth - 1, windowHeight - 1, window, 0, windowWidth)

        window.sortInPlace()

        bufferedOutputImage.setRGB(x, y, window(windowHeight * windowWidth / 2))
      }
    }

    bufferedOutputImage
  }
}
