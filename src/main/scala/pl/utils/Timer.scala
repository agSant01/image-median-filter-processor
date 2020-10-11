package pl.utils

class Timer {
  private var startTime: Option[Long] = None
  private var seconds: Option[String] = None

  def start(): Unit = {
    this.startTime = Option(System.nanoTime)
  }


  def endTimer(): Unit = {
    val duration = (System.nanoTime - this.startTime.getOrElse(0.toLong)) / 1e9d
    this.startTime = None
    this.seconds = Option(f"$duration seconds")
  }

  def getSeconds: String = {
    this.seconds.getOrElse("")
  }
}
