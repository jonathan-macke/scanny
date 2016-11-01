package org.scanny

import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._
import Helpers._


object MongoTest extends App {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost")

  val database: MongoDatabase = mongoClient.getDatabase("mydb");    
  val collection: MongoCollection[Document] = database.getCollection("myCollection");    
  
  collection.drop().results()
  
  val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
                             "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102)) 
                             
   collection.insertOne(doc).results();                          
}