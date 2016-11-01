package org.scanny.app

import org.mongodb.scala.MongoClient
import org.mongodb.scala.MongoDatabase
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson._
import org.bson.BsonDocument
import org.scanny.Product

object GenerateHTML extends App {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
  val database: MongoDatabase = mongoClient.getDatabase("scanny");
  val collection: MongoCollection[Document] = database.getCollection("products");

  
  val count = collection.count()
  count.subscribe((ct: Long) => println(ct))
  
  val findObservable = collection.find().collect()
  findObservable.subscribe(print(_))

  def print(results: Seq[Document]) {
    for (result <- results){
      
      val doc = Product(imgLink = result.getOrElse("imgLink", "").asInstanceOf[BsonString].getValue(), 
            marque = result.getOrElse("marque", "").asInstanceOf[BsonString].getValue(), 
            name = result.getOrElse("name", "").asInstanceOf[BsonString].getValue(), 
            prix = result.getOrElse("prix", "").asInstanceOf[BsonString].getValue(), 
            prixKilo = result.getOrElse("prixKilo", "").asInstanceOf[BsonString].getValue(), 
            quantite = result.getOrElse("quantite", "").asInstanceOf[BsonString].getValue()) 
         
     println(doc)
    }
  }

  Thread.sleep(10000000)
  
}





