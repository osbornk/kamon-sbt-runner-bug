package com.centraldesktop.virusscanner

import java.util.UUID

import kamon.Kamon
import kamon.trace.Tracer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Boot {
  def main(args: Array[String]): Unit = {
    def getTraceToken = Tracer.currentContext.collect(_.token).getOrElse("")

    Kamon.start()

    val traceToken = UUID.randomUUID.toString

    Tracer.withNewContext("myTrace", Some(traceToken), Map.empty[String, String], autoFinish = true) {
      System.out.println(s"Outside future: $getTraceToken")

      val res = Future {
        System.out.println(s"Inside future: $getTraceToken")
      }.map(_ => System.out.println(s"Map of future: $getTraceToken"))

      Await.ready(res, 10.seconds)
      System.out.println(s"Completed future: $getTraceToken")
    }
  }
}
