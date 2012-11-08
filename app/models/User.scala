package models

import play.api.db._
import anorm._
import play.api.Play.current

case class User(id: Long, email: String, firstName: String, lastName: String)

object User {
  def all(): List[User] = Nil

  def findBy(userId: Long): Option[User] = {
    DB.withConnection { implicit c =>
      SQL("select * from user where id = {userId}").on('userId -> userId).as(user.singleOpt)
    }
  }

  def create(email: String, firstName: String, lastName: String) = {
    DB.withConnection { implicit c =>
      SQL("insert into user (email, firstName, lastName) values ({email}, {firstName}, {lastName})").on('email -> email, 'firstName -> firstName, 'lastName -> lastName
      ).executeUpdate()
    }
  }


  import anorm._
  import anorm.SqlParser._

  val user = {
    get[Long]("id") ~ get[String]("email") ~ get[String]("firstName") ~ get[String]("lastName") map {
      case id ~ email ~ firstName ~ lastName => User(id, email, firstName, lastName)
    }
  }

}
