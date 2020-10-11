package pl

import org.scalatest.funsuite.AnyFunSuite
import pl.utils.FileReader

class FileReaderTest extends  AnyFunSuite {
  test("readPicture") {
    val reader = new FileReader("C:\\Users\\gabri\\Documents\\University Related\\2020 Fall\\Programing Languages\\5 - A\\PLAssignment4\\src\\test\\resources\\pic.jpg")

    assert {
      reader.getBufferedImage != null
    }
  }

  test("getDirectory") {
    val reader = new FileReader("C:\\Users\\gabri\\Documents\\University Related\\2020 Fall\\Programing Languages\\5 - A\\PLAssignment4\\src\\test\\resources\\pic.jpg")


    assert {
      reader.getAbsoluteDirectory == "C:\\Users\\gabri\\Documents\\University Related\\2020 Fall\\Programing Languages\\5 - A\\PLAssignment4\\src\\test\\resources\\"
    }
  }
}
