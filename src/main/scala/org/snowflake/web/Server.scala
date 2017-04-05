package org.snowflake.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.{Source, StdIn}

object Server {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val raw = Source.fromInputStream(getClass.getResourceAsStream("/response.json")).mkString

    val route =
      path("api" / "v1" / "test") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, raw))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8008)

    println(s"Server online at http://localhost:8008/\nPress RETURN to stop...")
//    StdIn.readLine() // let it run until user presses return
//    bindingFuture
//      .flatMap(_.unbind()) // trigger unbinding from the port
//      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
