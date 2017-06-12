/*
rewrites = ExplicitReturnTypes
explicitReturnTypes.memberKind = [Def, Var]
explicitReturnTypes.memberVisibility = [Public, Protected]
 */
package test

object ExplicitReturnTypes {
  val a = 1
  def b() = List
  var c = true
  protected val d = 1.0f
  protected def e(a:Int,b:Double) = a + b
  protected var f = (x: Int) => x + 1
  private val g = 1
  private def h(a:Int) = ""
  private var i = 1

  implicit val L = List(1)
  implicit val M = Map(1 -> "STRING")
  implicit def D = 2
  implicit def tparam[T](e: T) = e
  implicit def tparam2[T](e: T) = List(e)
  implicit def tparam3[T](e: T) = Map(e -> e)
  class Path {
    class B { class C }
    implicit val x = new B
    implicit val y = new x.C
  }
  class TwoClasses[T](e: T)
  class TwoClasses2 {
    implicit val x = new TwoClasses(10)
  }
  class implicitlytrick {
    implicit val s = "string"
    implicit val x = implicitly[String]
  }
  object InnerInnerObject {
    object B {
      class C
      object C {
        implicit val x = List(new C)
      }
    }
  }
  object SiblingObject {
    class B
  }
  object A {
    class C {
      implicit val x = List(new SiblingObject.B)
    }
  }
  object slick {
    case class Supplier(id: Int, name: String)
    implicit val supplierGetter = (arg: (Int, String)) =>
      Supplier(arg._1, arg._2)
  }
}

class DeeperPackage[T](e: T)
package foo {
  class B {
    implicit val x = new DeeperPackage(10)
  }
}

package shallwpackage {
  class A[T](e: T)
}
class shallobpackage {
  implicit val x = new shallwpackage.A(10)
}

package enclosingPackageStripIsLast { class B }
package a {
  import enclosingPackageStripIsLast.B
  class A {
    implicit val x = new B
  }
}
package factory{
  private [factory] class Factory
  object Factory{
    def f() = new Factory()
  }
}
