
import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._

class TweetsTesting extends FunSuite {

  val obj = new GetTweets
  test("retrieveTweets") {
       assert(Await.result(obj.retrieveTweets("#Scala"),10.second))
  }

  test("countNumberOfTweets") {
       assert(Await.result(obj.countNumberOfTweets("#Scala"),10.second))
  }

  test("averageTweetsPerDay") {
       assert(Await.result(obj.averageTweetsPerDay("#Scala"),10.second))
  }
  test("total Retweets") {
       assert(Await.result(obj.countNoOfRetweets("#Scala"),10.second))
  }
  test("total Likes") {
       assert(Await.result(obj.countNoOfLikes("#Scala"),10.second))
  }


}
