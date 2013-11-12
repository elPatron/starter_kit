package controllers

import play.api.mvc._
import play.api.DefaultGlobal
import s4.BuildInfo._


abstract class AppInfoController() extends Controller {

  def getVersion() = Action {
    Ok(s"${name} : ${version} : ${organization}")
  }
  def shutDown() = Action {
  	DefaultGlobal.onStop(play.api.Play.current)
    Ok("GoodBye!")
  }
}

object AppInfoController extends AppInfoController