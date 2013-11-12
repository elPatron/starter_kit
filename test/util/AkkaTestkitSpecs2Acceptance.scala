package util

import org.specs2.mutable._
import akka.actor._
import akka.testkit._
import akka.testkit.TestKit._
import org.specs2.time.NoTimeConversions
import shapeless.Typeable._
import shapeless.Typeable
import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

trait IAkkaSpecification extends Specification with NoTimeConversions

abstract class AkkaTestkitSpecs2(systemName: String = "")
  extends TestKit(systemName.length > 0 match {
    case true => ActorSystem(systemName)
    case false => ActorSystem()
  }) {
  dilated(10 milliseconds, system)
}

abstract class AkkaTestkitSpecs2Unit(systemName: String = "") extends AkkaTestkitSpecs2(systemName)
with After with ImplicitSender {

  def after = system.shutdown()
}


case class AkkaTestkitSpecs2Acceptance(systemName: String = "") extends AkkaTestkitSpecs2(systemName)

/*
This was written out of frustration with Akka's test kit
There is no need to throw exceptions when you are looking for messages!
All we care about is whether a message was received or not!
 */
abstract class AkkaSpecs2[T](typeableT: Typeable[T], systemName: String = "") {

  implicit val t = typeableT
  val underlyingKit = new TestKit(systemName.length > 0 match {
    case true => ActorSystem(systemName)
    case false => ActorSystem()
  })
  val system = underlyingKit.system
  dilated(15 milliseconds, system)
  protected val allMessages = scala.collection.mutable.Queue.empty[T]

  val specsActor = system.actorOf(Props(() => new SpecsActor()))

  def msgExists[M <: T](implicit typeable: Typeable[M]): Boolean = {
    allMessages.exists(tMsg => tMsg.cast[M](typeable).exists(_ => true))
  }

  def msgExists[M <: T](duration: Duration)(implicit typeable: Typeable[M]): Boolean = {
    Thread.sleep(duration.toMillis)
    msgExists[M](typeable)
  }

  def clearMsgs = allMessages.clear()

  class SpecsActor extends Actor {
    def receive = {
      case any =>
        any.cast[T].map(msgOfInterest =>
          allMessages.enqueue(msgOfInterest))
    }
  }

}

case class AkkaSpecs2Acceptance[T](systemName: String = "")(implicit typeable: Typeable[T]) extends AkkaSpecs2[T](typeable, systemName)



