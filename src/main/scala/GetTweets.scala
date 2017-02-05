import org.apache.log4j.Logger
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class  GetTweets {
  val obj = new Snippet
  val log = Logger.getLogger(this.getClass)

 def retrieveTweets (search:String): Future[Boolean] =Future{
   val list = obj.getTweets(search)
   val lst=list._1.toString
   log.info(lst)
   true
 }
  def countNumberOfTweets(search:String): Future[Boolean] =Future{
    val list = obj.getTweets(search)
    val count=list._1.size.toString
    log.info(count)

    true
  }
  def averageTweetsPerDay(search:String): Future[Boolean] =Future{
    val list = obj.getTweets(search)
    val map= list._1.groupBy(_.createdAt).mapValues{x=>x.size}.toString()
    log.info(map)
    true
  }
  def countNoOfRetweets(search:String): Future[Boolean] =Future{
    val list = obj.getTweets(search)
  val retweets = list._2.map{tweet=>tweet.getRetweetCount()}
    log.info(retweets.sum.toString)
    true

  }
  def countNoOfLikes(search:String):Future[Boolean] =Future{
    val list = obj.getTweets(search)
    val likes = list._2.map{tweet=>tweet.getFavoriteCount()}
    log.info(likes.sum.toString)
    true

  }

}
