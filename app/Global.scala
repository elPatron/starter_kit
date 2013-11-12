import play.api._
import play.api.Play._
import scala.concurrent.ExecutionContext.Implicits.global

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    if (mode != Mode.Test) {
    }
    Logger info "Application has started"

    super.onStart(app)
  }

  override def onStop(app: Application) {
    Logger info "Application has stopped"

    if (mode != Mode.Test) {

    }
    super.onStop(app)
  }
}


