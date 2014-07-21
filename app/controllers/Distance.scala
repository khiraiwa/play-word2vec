package controllers

import scala.sys.process._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.data.Form
import play.api.data.Forms._

object Distance extends Controller {

  System.load("/home/vagrant/Documents/play-word2vec/app/controllers/libDistanceJni.so");
  
  val form = Form( "name" -> text )
  def distanceApi = Action { implicit request =>
    val name = form.bindFromRequest.get

    val args:Array[String] = name.split(",")
    /*
    val commandArg = args.mkString(" ")

    val result = Process("/home/vagrant/word2vec/trunk/execute_distance.sh " + commandArg) !!

    if (result.contains("Out of dictionary word!")) {
      println("error!")
    } else {
      println("success")
    }
    * 
    */
    val jni:DistanceJni = new DistanceJni()
    val param:Array[String] = Array("tmp", "/home/vagrant/word2vec/trunk/jawiki.bin","ソニー", "パナソニック", "シャープ")
    val ret  = jni.adapter(param);
    Ok(ret)

    /*
    var resultArrayTmp = result.split("------------------------------------------------------------------------")
    var resultTmp = resultArrayTmp(1)
    resultArrayTmp = resultTmp.split("Enter word or sentence")
    resultTmp = resultArrayTmp(0)
    var distancesArray = resultTmp.split("\n")
    var map:Map[String,String] = Map()
    distancesArray.foreach { i =>
      val j = i.split("\t")
      var list = List()
      var key:String = null
      var value:String = null
      j.foreach { k =>
        if (!k.trim().isEmpty()) {
          if (key == null) {
            key = k.trim()
          } else {
            value = k.trim()
          }
          map = map.updated(key, value)
        }
      }
    }
    
    map.foreach{case(key, value) =>
      println(key + ":" + value)
    }
    

    val jsonObject = Json.toJson(map)

    Ok(jsonObject.toString)
    * 
    */
  }

}