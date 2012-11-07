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

  def login(userId: String) = Action {implicit request =>
    Ok(views.html.Application.login(User(userId, "x", "x", "x")))
  }

  def newUser = Action { implicit request =>
    registrationForm.bindFromRequest().fold(
     errors => BadRequest(views.html.Application.register(registrationForm)),
     value => {
       val user: User = User.create(value._1, value._2, value._3)
       Redirect(routes.Application.login(user.id))
     }
    )

  }

  import play.api.data._
  import play.api.data.Forms._

  val registrationForm = Form(tuple(
    "email" -> nonEmptyText,
    "firstName" -> nonEmptyText,
    "lastName" -> nonEmptyText
   )
  )
}

