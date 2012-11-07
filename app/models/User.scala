package models

import java.util.UUID

case class User(id: String, email: String, firstName: String, lastName: String)

object User {
  def all(): List[User] = Nil

  def create(email: String, firstName: String, lastName: String) = {
       User(UUID.randomUUID().toString, email, firstName, lastName)
  }
}
