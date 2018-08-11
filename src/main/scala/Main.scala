import cats.kernel.Monoid
import cats.instances._
object Main extends App {
  implicit val booleanEitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean = false
  }
  val bm = Monoid[Boolean].combine(true, true)
  println (bm)
}