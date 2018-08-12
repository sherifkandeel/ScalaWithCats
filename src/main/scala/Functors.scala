package de.zalando.workflow.clients.task
import scala.language.higherKinds
import cats.Functor
import cats.instances.list._   // for Functor
import cats.instances.option._ // for Functor

object functors {
  val list1 = List(1, 2, 3)
  // list1: List[Int] = List(1, 2, 3)
  val list2 = Functor[List].map(list1)(_ * 2)
  // list2: List[Int] = List(2, 4, 6)
  val option1 = Option(123)
  // option1: Option[Int] = Some(123)
  val option2 = Functor[Option].map(option1)(_.toString) // option2: Option[String] = Some(123)

  val foption3 = Functor[Option].lift[Int, Int](x => x*2)
  foption3(Some(2))

}

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
  def map[A, B] (tree: Tree[A] ) (func: A => B): Tree[B] = tree match {
  case Branch (left, right) => Branch (map (left) (func), map (right) (func) )
  case Leaf (value) => Leaf (func (value) )
}
}

