package controllers

import play.api._
import play.api.mvc._
import models.User

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def register = Action {
    Ok(views.html.Application.register(registrationForm))
  }

  def login(userId: Long) = Action {
    implicit request =>
      User.findBy(userId) match {
        case Some(user) => Ok(views.html.Application.login(user))
        case _ => Redirect(routes.Application.register())
      }
  }

  def newUser = Action {
    implicit request =>
      registrationForm.bindFromRequest().fold(
        errors => BadRequest(views.html.Application.register(registrationForm)),
        value => {
          val userId: Long = User.create(value._1, value._2, value._3, value._4)
          Redirect(routes.Application.login(userId))
        }
      )

  }

  import play.api.data._
  import play.api.data.Forms._

  val registrationForm = Form(tuple(
    "email" -> nonEmptyText,
    "firstName" -> nonEmptyText,
    "lastName" -> nonEmptyText,
    "password" -> nonEmptyText
  )
  )
}

